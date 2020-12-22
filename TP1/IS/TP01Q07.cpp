#include <stdbool.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define true     1
#define false    0
#define NUMENTRADA 1024
#define TAMLINHA 1024

bool isVogal(char* s);
bool isConsoante(char* s);
bool isFim(char* s);
bool isInteiro(char* s);
bool isReal(char* s);

bool isFim(char* s){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

bool isVogal(char* s){
    bool resp = false;
    int aux = 0;

    for(int i = 0; i < strlen(s); i++){
        if(s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U' || s[i] == 'u' || s[i] == 'o' || s[i] == 'i' || s[i] == 'e' || s[i] == 'a'){
            aux++;
        }     
    }
    if(aux >= strlen(s) -1){
            resp = true;
        }else
            resp = false;

    return resp;
}

bool isConsoante(char s[]){
    bool resp = false;
    int aux = 0;

    for(int i = 0; i < strlen(s); i++){
        if(s[i] == 'A' || s[i] == 'a' || s[i] == 'e' || s[i] == 'E' || s[i] == 'i' || s[i] == 'I' || s[i] == 'o' || s[i] == 'O' || s[i] == 'u' || s[i] == 'U'){
            aux++;
        }else if( s[i] >= '0' ||  s[i] <= '9'){
            aux++;
        }
    }
    if(aux > 0){
        resp = false;
    }else
        resp = true;
    
    return resp;
}

bool isInteiro(char s[]){
    bool resp = true;
    int aux = 0;

    for(int i = 0; i < strlen(s); i++){
        if(s[i] < '0' || s[i] > '9'){
            aux++;
        }else if((s[i] - (int) s[i]) != 0){
            aux++;
        }    
    }
    printf("%d", (int)s[1]);
    if(aux > 0){
        resp = false;
    }else 
        resp = true;

    return resp;
}

bool isReal(char* s){
    bool resp = true;
    int aux = 0;

    for(int i = 0; i < strlen(s); i++){
        if(s[i] < '0' || s[i] > '9'){
            if(s[i] == '.' || s[i] == ',')
                aux++;
            else{
                resp = false;
                i = strlen(s);   
            }
        }
    }
    if(aux > 1)
        resp = false;

    return resp;   
}



int main(int args, char** argv) {
    char entrada[NUMENTRADA][TAMLINHA];
    int numEntrada = 0;

    do{
        fgets(entrada[numEntrada], TAMLINHA, stdin);
    } while (isFim(entrada[numEntrada++]) == false);
    numEntrada--;

    for(int i = 0; i < numEntrada; i++){
        if(isVogal(entrada[i]) == true){
            printf("SIM ");
        }else
            printf("NAO ");
        if(isConsoante(entrada[i]) == true){
            printf("SIM ");
        }else
            printf("NAO ");
        if(isInteiro(entrada[i]) == true){
            printf("SIM ");
        }else
            printf("NAO ");
        if(isReal(entrada[i]) == true){
            printf("SIM\n");
        }else
            printf("NAO\n");        
        
    }
    return 0;

} 
