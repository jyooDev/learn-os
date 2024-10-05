# Sockets
> In operating systems, a socket is an endpoint for communication between two machines or processes. Sockets are used to establish communication links between different systems, both within a single machine or across a network, using standard protocols like TCP (Transmission Control Protocol) or UDP (User Datagram Protocol).

## Key Points about Sockets:
- **Endpoint**: A socket represents one end of a two-way communication link.
- **Communication Types**: 
    - Stream-based communication (TCP): Ensures reliable, ordered, and error-checked delivery of data.
    - Datagram-based communication (UDP): Focuses on fast, connectionless data transmission with minimal overhead but without guarantees of delivery or order.
- **IP Address & Port**: A socket is usually associated with an IP address and a port number, which uniquely identifies a communication channel.
- **System Calls**: Operating systems provide system calls for creating and managing sockets, such as `socket()`, `bind()`, `listen()`, `connect()`, and `accept()`.

## Example Scenario
**Scenario -** You open Google Chrome, type in www.google.com, and press Enter.
1. DNS Lookup: Browser sends a DNS request (over a socket) to resolve www.google.com.
2. Client Socket Creation: The browser creates a client socket and connects to Google’s server on IP address and port.
3. TCP Handshake: The browser and Google's server establish a reliable connection using a three-way handshake.
4. TLS Handshake: A secure encrypted connection is established using TLS.
5. HTTP Request: The browser sends an HTTP GET request over the socket to request Google’s homepage.
6. Server Response: Google’s server sends the HTML page back over the same socket.
7. Rendering: The browser renders the HTML and makes additional requests for resources (if needed).
8. Socket Closure: Once the page is loaded, the browser and server close the socket.

