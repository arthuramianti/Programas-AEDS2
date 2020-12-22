#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

#define TAM_MAX_LINHA 250
#define NUMENTRADA 1000
#define TAMLINHA 1000
#define MAXTAM  1000     // Tamanho da lista de jogadores


typedef struct {
    int id;
    int peso;
    int altura;
    char nome[70];
    char universidade[70];
    int anoNascimento;
    char cidadeNascimento[70];
    char estadoNascimento[70];
} Jogador;

void inserirNaoInformado(char *linha, char *novaLinha){
    int tam = strlen(linha);
    for (int i = 0; i <= tam; i++, linha++) {
        *novaLinha++ = *linha;
        if (*linha == ',' && (*(linha + 1) == ',' || *(linha + 1) == '\0')) {
            strcpy(novaLinha, "nao informado");
            novaLinha += strlen("nao informado");
        }
    }
}

void tirarQuebraDeLinha(char linha[]) {
    int tam = strlen(linha);
    if (linha[tam - 1] == '\r' || linha[tam - 1] == '\n') // Mac ou Linux
        linha[tam - 1] = '\0'; // Apaga a linha
}

void ler(Jogador *jogador, char linha[]) {
    char novaLinha[TAM_MAX_LINHA];
    tirarQuebraDeLinha(linha);
    inserirNaoInformado(linha, novaLinha);

    jogador->id = atoi(strtok(novaLinha, ","));
    strcpy(jogador->nome, strtok(NULL, ","));
    jogador->altura = atoi(strtok(NULL, ","));
    jogador->peso = atoi(strtok(NULL, ","));
    strcpy(jogador->universidade, strtok(NULL, ","));
    jogador->anoNascimento = atoi(strtok(NULL, ","));
    strcpy(jogador->cidadeNascimento, strtok(NULL, ","));
    strcpy(jogador->estadoNascimento, strtok(NULL, ","));
}

void imprimir(Jogador *jogador) {
    printf(" ## %s ## %d ## %d ## %d ## %s ## %s ## %s",
        jogador->nome,
        jogador->altura,
        jogador->peso,
        jogador->anoNascimento,
        jogador->universidade,
        jogador->cidadeNascimento,
        jogador->estadoNascimento
    );
}

Jogador clone(Jogador *jogador) {
    Jogador retorno;

    retorno.id = jogador->id;
    strcpy(retorno.nome, jogador->nome);
    retorno.altura = jogador->altura;
    retorno.peso = jogador->peso;
    retorno.anoNascimento =  jogador->anoNascimento;
    strcpy(retorno.universidade, jogador->universidade);
    strcpy(retorno.cidadeNascimento, jogador->cidadeNascimento);
    strcpy(retorno.estadoNascimento, jogador->estadoNascimento);

    return retorno;
}

bool isFim(char s[]){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

Jogador lista[MAXTAM];   //Lista
int n;                   //Quantidade de elementos na lista

void start(){             //Inicializador
    n = 0;
}

void inserirInicio(Jogador *jogador){
    int i;

    if(n >= MAXTAM){              //Valida inserçao
        printf("Erro, lista cheia");
        exit(1);
    }

    for(i = n; i > 0; i--){
        lista[i] = lista[i-1];
    }

    lista[0] = *jogador;
    n++;

}

void inserirFim(Jogador *jogador){
    if(n >= MAXTAM){              //Valida inserçao
        printf("Erro, lista cheia");
        exit(1);
    }

    lista[n] = *jogador;
    n++;
}

void inserir(Jogador *jogador, int pos){
    int i;

    if(n >= MAXTAM || pos < 0 || pos > n){
       printf("Erro ao inserir!");
       exit(1);
    }

    for(i = n; i > pos; i--){
       lista[i] = lista[i-1];
    }

    lista[pos] = *jogador;
    n++;
}

Jogador removerInicio(){
    int i;
    Jogador resp;
    
    if (n == 0) {
       printf("Erro ao remover!");
       exit(1);
    }

    resp = lista[0];
    n--;

    for(i = 0; i < n; i++){
       lista[i] = lista[i+1];
    }

    return resp;
}

Jogador removerFim() {
    if (n == 0) {
       printf("Erro ao remover!");
       exit(1);
    }

   return lista[--n];
}

Jogador remover(int pos) {
   int i;
   Jogador resp;

    if (n == 0 || pos < 0 || pos >= n) {
       printf("Erro ao remover!");
       exit(1);
    }

    resp = lista[pos];
    n--;

    for(i = pos; i < n; i++){
       lista[i] = lista[i+1];
    }

    return resp;
}

void mostrar (){
    int i;

    for(i = 0; i < n; i++){
        printf("[");
        printf("%d", i);
        printf("]");
        imprimir(&lista[i]);
        printf(" ##\n");
    }

}


int main(void){
    char entrada[NUMENTRADA][TAMLINHA];
    int numEntrada = 0;

    do{
        fgets(entrada[numEntrada],TAMLINHA, stdin);
    }while (isFim(entrada[numEntrada++]) == false);
    numEntrada--;

    char referencia[3923][200];
    int cont = 0;

    FILE * file;
    file = fopen("/tmp/players.csv" , "r");
    while(cont < 3923){
        fgets(referencia[cont],200, file);
        cont++;
    }

    Jogador jogador[numEntrada];
    start();

    for(int i = 0; i < numEntrada; i++){
        ler(&jogador[i], referencia[atoi(entrada[i]) + 1]);
        inserirFim(&jogador[i]);
    }
    
    char ref[2];
    fgets(ref, 3, stdin);
    int numOperacoes = atoi(ref);
    
    fgets(entrada[60], TAMLINHA, stdin);            //Usei isso para pular o \n

    for(int i = 0; i < numOperacoes; i++){
        fgets(entrada[i], TAMLINHA, stdin);
        char token[4];
        strcpy(token, strtok(entrada[i], " "));
        if(strcmp(token, "II") == 0){
            ler(&jogador[i], referencia[atoi(strtok(NULL, " ")) + 1]);
            inserirInicio(&jogador[i]);
        }else if(strcmp(token, "IF") == 0){
            ler(&jogador[i], referencia[atoi(strtok(NULL, " ")) + 1]);
            inserirFim(&jogador[i]);
        }else if(strcmp(token, "I*") == 0){
            char posi[4];
            strcpy(posi, strtok(NULL, " "));
            int pos = atoi(posi);
            ler(&jogador[i], referencia[atoi(strtok(NULL, " ")) + 1]);
            inserir(&jogador[i], pos);
        }else if(strcmp(token, "R*") == 0){
            char posi[4];
            strcpy(posi, strtok(NULL, " "));
            int pos = atoi(posi);
            printf("(R) ");
            printf("%s", remover(pos).nome );
            printf("\n");
        }else if(strcmp(token, "RI\n") == 0){
            printf("(R) ");
            printf("%s", removerInicio().nome );
            printf("\n");
        }else if(strcmp(token, "RF\n") == 0){
            printf("(R) ");
            printf("%s", removerFim().nome );
            printf("\n");
        } 

        
    }
    mostrar();

    

}




