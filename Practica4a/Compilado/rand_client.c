/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "rand.h"


void
rand_prog_1(char *host, long semilla, int itera)
{
	CLIENT *clnt;
	void  *result_1;
	long  inicializa_random_1_arg;
	long  *result_2;
	char *obtiene_siguiente_random_1_arg;
	int i;
	
	//CODIGO DE PRUEBA
	printf("La semilla es %ld y se haran %d iteraciones\n", semilla, itera);

#ifndef	DEBUG
	clnt = clnt_create (host, RAND_PROG, RAND_VERS, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}
#endif	/* DEBUG */
	inicializa_random_1_arg = semilla;
	result_1 = inicializa_random_1(&inicializa_random_1_arg, clnt);
	if (result_1 == (void *) NULL) {
		clnt_perror (clnt, "call failed");
	}
	for(i = 0; i < itera; i++){
		result_2 = obtiene_siguiente_random_1(result_1/*(void*)&obtiene_siguiente_random_1_arg*/, clnt);
		if (result_2 == (long *) NULL) {
			clnt_perror (clnt, "call failed");
		}
		printf("%d : %ld\n", i, *result_2);
	}
	
#ifndef	DEBUG
	clnt_destroy (clnt);
#endif	 /* DEBUG */
}


int
main (int argc, char *argv[])
{
	int semilla,itera;
	char *host;
	if (argc < 2) {
		printf ("usage: %s server_host\n", argv[0]);
		exit (1);
	}
	host = argv[1];
	semilla = (long)atoi(argv[2]);
	itera = atoi(argv[3]);
	rand_prog_1 (host,semilla, itera);
exit (0);
}
