import { useState, useEffect, useRef } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";
import "./App.css";

function App() {
  const [messages, setMessages] = useState([]);
  const [username, setUsername] = useState("");
  const [messageInput, setMessageInput] = useState("");

  const socketRef = useRef()

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/chat");

    const client = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 5000,
    });

    client.onConnect = () => {
      client.subscribe('/topic/messages', (msg) => {
        setMessages((prevMessages) => [...prevMessages, msg.body])
      })
    }

    client.onStompError = (frame) => {
      console.log('STOMP Error:', frame)
    }

    client.activate();

    socketRef.current = client;

    return () => {
      client.deactivate();
    }

  }, []);

  function handleSubmit(e) {
    e.preventDefault();

    if(messageInput.trim() && username.trim()) {
      socketRef.current.publish({
        destination: '/app/chat',
        body: JSON.stringify({message: messageInput, user: username})
      })
      setMessageInput('')
    }
  }

  return (
    <>
      <div>
        <h1>Chat App</h1>
        <ul>
          {
            messages.map((msg, index) => {
              return(
                <li key={index}>{msg}</li>
              )
            })
          }
        </ul>
        <form
          style={{ display: "flex", flexDirection: "column" }}
          onSubmit={handleSubmit}
        >
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Username"
          />
          <input
            type="text"
            value={messageInput}
            onChange={(e) => setMessageInput(e.target.value)}
            placeholder="Message"
          />
          <button type="submit">Send Message</button>
        </form>
      </div>
    </>
  );
}

export default App;
