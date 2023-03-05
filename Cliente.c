#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <winsock2.h>

#pragma comment(lib, "ws2_32.lib")

int main() {
    // Inicializa Winsock
    WSADATA wsa;
    if (WSAStartup(MAKEWORD(2,2), &wsa) != 0) {
        printf("Error al inicializar Winsock: %d\n", WSAGetLastError());
        return 1;
    }

    // Crea el socket del cliente
    SOCKET sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == INVALID_SOCKET) {
        printf("Error al crear el socket: %d\n", WSAGetLastError());
        WSACleanup();
        return 1;
    }

    // Especifica la dirección IP y número de puerto del servidor
    struct sockaddr_in server_addr;
    memset(&server_addr, 0, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1"); // Dirección IP del servidor
    server_addr.sin_port = htons(1234); // Número de puerto del servidor

    // Conéctate al servidor
    int connect_result = connect(sockfd, (struct sockaddr *)&server_addr, sizeof(server_addr));
    if (connect_result == SOCKET_ERROR) {
        printf("Error al conectarse al servidor: %d\n", WSAGetLastError());
        closesocket(sockfd);
        WSACleanup();
        return 1;
    }
    printf("Conexión establecida con el servidor\n");

    // Envía mensajes al servidor y espera una respuesta
    char buffer[256];
    int n;
    while (1) {
        printf("Ingrese un mensaje: ");
        memset(buffer, 0, sizeof(buffer));
        fgets(buffer, sizeof(buffer), stdin);
        n = send(sockfd, buffer, strlen(buffer), 0);
        if (n == SOCKET_ERROR) {
            printf("Error al escribir en el socket: %d\n", WSAGetLastError());
            closesocket(sockfd);
            WSACleanup();
            return 1;
        }
        memset(buffer, 0, sizeof(buffer));
        n = recv(sockfd, buffer, sizeof(buffer), 0);
        if (n == SOCKET_ERROR) {
            printf("Error al leer del socket: %d\n", WSAGetLastError());
            closesocket(sockfd);
            WSACleanup();
            return 1;
        }
        printf("Mensaje recibido del servidor: %s\n", buffer);
    }

    // Cierra el socket y libera Winsock
    closesocket(sockfd);
    WSACleanup();
    return 0;
}
