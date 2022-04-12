import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import AuthService from '../services/auth.service';


const ENDPOINT = "http://localhost:8080/ws";

function Chat() {

    const [stompClient, setStompClient] = useState(null);
    const [chatToDisplay, setChatToDisplay] = useState([]);
    const [msgToSend, setSendMessage] = useState({
        content: "",
        username: AuthService.getUsername()
    });
    const chatMessages = [];


    useEffect(() => {
        const socket = SockJS(ENDPOINT);
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            // subscribe to the backend
            stompClient.subscribe('/topic/public', (data) => {
                // console.log(data.body);
                onMessageReceived(data);
                chatMessages.push(JSON.parse(data.body));
                setChatToDisplay(chatMessages);
                // console.log(chatMessages);
            });
        });
        setStompClient(stompClient);
         // eslint-disable-next-line
    }, []);


    // display the received data
    function onMessageReceived(data) {
         // eslint-disable-next-line
        const result = JSON.parse(data.body);
    };

    function send() {
        // chatMessages.push(msgToSend);
        stompClient.send("/app/chat.send", {}, JSON.stringify({ 'content': msgToSend.content, 'username': msgToSend.username }));
    }

    return (
        <div className="home">

            <div className='chatMessageContainer'>
                {
                    chatToDisplay.map((item) => {
                         return (<div className='singleMessageContainer'>
                            <h4>Username: {item.username}</h4>
                            <p key={item}>{item.content}</p>
                            {console.log(item.username)}
                        </div>);
                    })

                }
                <p>{chatMessages[0]}</p>
                <div className='chatMessageActions'>
                    <input className='chatMessageInput' onChange={(event) => setSendMessage({...msgToSend, content: event.target.value})} placeholder='Type your message here...'></input>

                    <button className='buttonSendChatMessage userAddButton' onClick={send}>Send Message</button>

                </div>
            </div>
        </div>
    )
}

export default Chat