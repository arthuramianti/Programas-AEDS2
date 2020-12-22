#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

#define TAM_MAX_LINHA 250
#define NUMENTRADA 1000
#define TAMLINHA 1000
#define MAXTAM  6     // Tamanho da lista de jogadores


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
    if (linha[tam - 1] == '\r' || linha[tam - 1] == '\n') 
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

Jogador fila[MAXTAM+1];
int primeiro;
int ultimo;

void start(){
    primeiro = ultimo = 0;
}


Jogador remover() { 

    //validar remocao
    if (primeiro == ultimo) {
       printf("Erro ao remover!");
       exit(1);
    }

    Jogador resp = fila[primeiro];
    primeiro = (primeiro + 1) % MAXTAM;
    return resp;
}

void inserir(Jogador *jogador) {

   //validar insercao
    if (((ultimo + 1) % MAXTAM) == primeiro) {
        remover();
    }

        fila[ultimo] = *jogador;
        ultimo = (ultimo + 1) % MAXTAM;
     
}

void mostrar (){
    int i;
    int aux = 0;
 
    for(i = primeiro; i != ultimo; i = ((i + 1) % MAXTAM)) {
       printf("[%i]", aux);
       imprimir(&fila[i]);
       printf(" ##\n");
       aux++;
    }
 
    
}

void media(){
    float somaAltura = 0;
        int aux = 0;
        float mediaAltura = 0;
        for(int i = primeiro; i != ultimo; i = ((i+1)) %MAXTAM){
            somaAltura += fila[i].altura;
            aux++; 
        }

        mediaAltura += somaAltura / aux;
        if((mediaAltura - (int)mediaAltura) < 0.5){
            printf("%d\n", (int)mediaAltura);
        }else
        {
            printf("%d\n", (int)(mediaAltura + 1));
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

    for(int i = 0; i < numEntrada; i++){                              //Insere todos os elementos ate o fim do pub.in e calcula a media arredondada das alturas ate entao
        ler(&jogador[i], referencia[atoi(entrada[i]) + 1]);
        inserir(&jogador[i]);
        media();
    }

    char ref[2];
    fgets(ref, 3, stdin);
    int numOperacoes = atoi(ref);
    fgets(entrada[60], TAMLINHA, stdin);

    for(int i = 0; i < numOperacoes; i++){
        fgets(entrada[i], TAMLINHA, stdin);
        char token[4];
        strcpy(token, strtok(entrada[i], " "));
        if(strcmp(token, "I") == 0){
            ler(&jogador[i], referencia[atoi(strtok(NULL, " ")) + 1]);
            inserir(&jogador[i]);
            media();
        }else{
            printf("(R) ");
            printf("%s", remover().nome );
            printf("\n");
        }     
    }

    mostrar();

    
}
