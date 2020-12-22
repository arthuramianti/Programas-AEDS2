#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define bool       short
#define true       1
#define false      0
#define equals(a, b)   (((strcmp(a, b) == 0) ? true : false)) 
#define NUMENTRADA 1024
#define TAMLINHA 1024

bool isFim(char* s);
bool isPalindromo(char* s);

bool isFim(char* s){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

bool isPalindromo(char* s){
    bool resp = true;
    
    s[strlen(s)-1] = '\0';

    for(int i = 0; i < strlen(s)/2; i++){
        if(s[i] != s[strlen(s) - 1 - i]){
          resp = false;
          i = strlen(s);
        }
    }
    return resp;
}

int main (int args, char ** argv){
    char entrada[NUMENTRADA][TAMLINHA];
    int numEntrada = 0;

    do{
        fgets(entrada[numEntrada], TAMLINHA, stdin);
    }while (isFim(entrada[numEntrada++]) == false);
    numEntrada--;

    for (int i = 0; i < numEntrada; i++){
        if(isPalindromo(entrada[i]) == true)
          printf("SIM\n");
        else
          printf("NAO\n");
          
    }
    return 0;
}
