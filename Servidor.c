#define WIN32_LEAN_AND_MEAN
#include <stdio.h>
#include <stdlib.h>
#include <winsock2.h>
#include <windows.h>
#include <ws2tcpip.h>

#pragma comment(lib,"ws2_32.lib")

int main(){
    WSADATA wsa;
    SOCKET s, new_socket;
    struct sockaddr_in server, client;
    int c;
    char *message; 

    printf("\nInicializando Winsock...\n");
    if(WSAStartup(MAKEWORD(2,2),&wsa) != 0){
        printf("Fallo. Error de codigo: %d", WSAGetLastError());
        return 1; 
    }
    printf("Inicializando.\n");

    //Creando el socket 
    if ((s=socket(AF_INET, SOCK_STREAM, 0)) == INVALID_SOCKET){
        printf("Fallo en la creacion del socket. Error de codigo: %d", WSAGetLastError());
        return 1; 
    }
    printf("Socket creado.\n");

    //Especifica la direccion del servidor
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(5000);

    //Enlaza el socket al puerto
    if(bind(s,(struct sockaddr *)&server, sizeof(server)) == SOCKET_ERROR){
        printf("Fallo al enalzar. Error de codigo: %d", WSAGetLastError());
        return 1;
    }    

    printf("Enlazado.\n");

    //Escucha las conexiones entrantes
    listen(s,3);

    //Acepta una conexion entrante 
    printf("Esperando por conexiones...");
    c = sizeof(struct sockaddr_in);
    while((new_socket = accept(s,(struct sockaddr*)&client, &c)) != INVALID_SOCKET){
        printf("Conexion aceptada.\n");

        //Responde al cliente 
        message = "Hola, Cliente. Soy el servidor. Hola desde el otro lado.";
        send(new_socket, message, strlen(message),0);
    }

    if(new_socket == INVALID_SOCKET){
        printf("Fallo al aceptar la conexion. Error de codigo: %d", WSAGetLastError());
        return 1;
    }

    //Cierra el socket
    closesocket(s);
    WSACleanup();

    return 0;
    
}