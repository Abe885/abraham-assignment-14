let username = sessionStorage.getItem('username');
console.log('Retrieved username from session storage:', username);

if (!username) {
    window.location.href = '/welcome';
} else {
    console.log('username already stored in session storage:', username);
}

function pollMessages(channelId) {
    fetch(`/messages/${channelId}`)
        .then(response => response.json())
        .then(messages => {
            const messageList = document.getElementById('messageList');
            messageList.innerHTML = '';
            messages.forEach(message => {
                const messageItem = document.createElement('li');
                messageItem.classList.add('message-item');

                const avatar = document.createElement('div');
                avatar.classList.add('message-avatar');
                messageItem.appendChild(avatar);

                const messageContent = document.createElement('div');
                messageContent.classList.add('message-content');

                const messageHeader = document.createElement('div');
                messageHeader.classList.add('message-header');

                if (message.user && message.user.username) {
                    const username = document.createElement('span');
                    username.classList.add('message-username');
                    username.textContent = message.user.username;
                    messageHeader.appendChild(username);
                } else {
                    console.error('User object is null or does not have a username:', message.user);
                }

                const timestamp = document.createElement('span');
                timestamp.classList.add('message-timestamp');
                const messageTimestamp = new Date(message.timestamp);
                timestamp.textContent = messageTimestamp.toLocaleString('en-US', {
                    year: 'numeric',
                    month: 'short',
                    day: 'numeric',
                    hour: '2-digit',
                    minute: '2-digit'
                });

                messageHeader.appendChild(timestamp);

                messageContent.appendChild(messageHeader);

                const messageText = document.createElement('p');
                messageText.classList.add('message-text');
                messageText.textContent = message.text;
                messageContent.appendChild(messageText);

                messageItem.appendChild(messageContent);
                messageList.appendChild(messageItem);
            });
        })
        .catch(error => {
            console.error('Error fetching messages:', error);
        });
}

function sendMessage(channelId) {
    const messageInput = document.getElementById('messageInput');
    const messageText = messageInput.value.trim();

    if (!messageText) {
        alert('Type a message!')
        return;
    }

    const message = {
        text: messageText,
        timestamp: new Date().toISOString(),
        user: { username: username },
        channel: { channelId: channelId }
    };
    fetch(`/messages/create`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(message)
    })
        .then(response => response.json())
        .then(data => {
            messageInput.value = '';
            pollMessages(channelId);
        })
        .catch(error => {
            console.error('Error sending message:', error);
        });
}

document.addEventListener('DOMContentLoaded', () => {
    const channelId = document.getElementById('channelId').value;
    setInterval(() => pollMessages(channelId), 1000);

    const messageForm = document.getElementById('messageForm');
    messageForm.addEventListener('submit', (event) => {
        event.preventDefault();
        sendMessage(channelId);
    });
});
