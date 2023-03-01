#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <winsock2.h>

#pragma comment(lib, "ws2_32.lib")
int main(int argc, char *argv[]){
    WSADATA wsa;
    SOCKET s;
    struct sockaddr_in server;
    char *message, server_reply[2000];
    int recv_size;

    printf("\nInicializando Winsock...");
    if(WSAStartup(MAKEWORD(2,2),&wsa) != 0){
        printf("Fallo. Error de codigo: %d", WSAGetLastError());
        return 1;
    }

    printf("Inicializando.\n");

    //Crea el socket
    if((s = socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET){
        printf("Falla la creacion del socket. Error de codigo: %d", WSAGetLastError());
        return 1;

    }

    printf("Socket creado.\n");

    //Especifica la direccion del servidor 
    server.sin_addr.s_addr = inet_addr("127.0.0.1");
    server.sin_family = AF_INET;
    server.sin_port = htons(5000);

    //Conecta al servidor 
    if (connect(s,(struct sockaddr*)&server, sizeof(server)) < 0){
        puts("Conexion fallida");
        return 1;
    }
    
    puts("Conectado");

    //Envi datos al servidor
    message = "Hola, Servidor. Soy el cliente.";
    if(send(s,message, strlen(message),0) < 0){
        puts("Envio fallido");
        return 1;
    }

    puts("Datos enviados al servidor");

    //Recibe una respuesta del servidor
    if((recv_size = recv(s, server_reply, 2000, 0)) == SOCKET_ERROR){
        puts("Recibe fallido");
    }

    puts("Respuesta recibida del servidor");
    printf("%s\n", server_reply);

    //Cierra el socket
    closesocket(s);
    WSACleanup();
    return 0;
}
