#include <stdio.h>
#include <winsock2.h>
#include <string.h>

int main() {
    WSADATA wsa;
    SOCKET s, new_socket;
    struct sockaddr_in server, client;
    int c, recv_size;
    int num;
    char message[2000], reply[2000];

    // Inicializar la biblioteca de sockets de Windows
    if (WSAStartup(MAKEWORD(2,2),&wsa) != 0) {
        printf("Error al inicializar Winsock\n");
        return 1;
    }

    // Crear el socket del servidor
    if ((s = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET) {
        printf("Error al crear el socket del servidor\n");
        return 1;
    }

    // Configurar la estructura del servidor
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(8888);

    // Vincular el socket del servidor al puerto
    if (bind(s,(struct sockaddr *)&server, sizeof(server)) == SOCKET_ERROR) {
        printf("Error al vincular el socket del servidor\n");
        return 1;
    }

    // Escuchar las conexiones entrantes
    listen(s, 3);

    // Esperar las conexiones entrantes de los clientes
    puts("Esperando conexiones entrantes...");
    c = sizeof(struct sockaddr_in);
    while ((new_socket = accept(s, (struct sockaddr *)&client, &c))) {
        puts("Conexión aceptada");

        // Recibir el mensaje del cliente
        memset(message, 0, 2000);
        if ((recv_size = recv(new_socket, message, 2000, 0)) == SOCKET_ERROR) {
            puts("Error al recibir el mensaje del cliente");
            return 1;
        }
        num=atoi(message);
        
        // Imprimir el mensaje recibido del cliente
        printf("Mensaje recibido del cliente: %d\n", num);
        // Enviar una respuesta al cliente
        itoa(num+1,reply,10);
        //strcpy(reply, );
        if (send(new_socket, reply, strlen(reply), 0) == SOCKET_ERROR) {
            puts("Error al enviar la respuesta al cliente");
            return 1;
        }

        // Cerrar la conexión con el cliente
        closesocket(new_socket);
    }

    if (new_socket == INVALID_SOCKET) {
        printf("Error al aceptar la conexión\n");
        return 1;
    }

    closesocket(s);
    WSACleanup();

    return 0;
}