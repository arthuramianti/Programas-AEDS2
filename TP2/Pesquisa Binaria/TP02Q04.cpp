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
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
        jogador->id,
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

int PesquisaBinaria (Jogador vet[], char chave[], int Tam)
{
     int inf = 0;     // limite inferior (o primeiro índice de vetor em C é zero          )
     int sup = Tam-1; // limite superior (termina em um número a menos. 0 a 9 são 10 números)
     int meio;
     
     while (inf <= sup)
     {
          meio = (inf + sup)/2;
          //printf("%s", vet[meio].nome);
          if (strcmp(chave , vet[meio].nome) == 0){
               return 1;
               inf = sup;
          }
          if (strcmp(chave , vet[meio].nome) < 0){
               sup = meio-1;
          }else
               inf = meio+1;
     }
     
     return 0;   
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

    for(int i = 0; i < numEntrada; i++){                              //Insere todos os elementos ate o fim do pub.in e calcula a media arredondada das alturas ate entao
        ler(&jogador[i], referencia[atoi(entrada[i]) + 1]);
        //imprimir(&jogador[i]);
    }

    for (int i = (numEntrada - 1); i > 0; i--) {
        for (int j = 0; j < i; j++) {
            if (strcmp(jogador[j].nome,jogador[j + 1].nome) > 0) {
                Jogador a = jogador[j];
                jogador[j]=jogador[j+1];
                jogador[j+1]=a;
            }
        }
    }

    int nomes = 0;
    char nom[NUMENTRADA][TAMLINHA];

    do{
        fgets(nom[nomes], TAMLINHA, stdin);
    }while(isFim(nom[nomes++]) == false);
    nomes--;

    for(int j = 0; j < nomes ; j++){
        for(int i=0; i<strlen(nom[j]); i++){
            if(nom[j][i]=='\n'){
                nom[j][i]='\0';
            }   
        }
    }

    for(int i = 0; i < nomes; i++){
        if(PesquisaBinaria(jogador, nom[i], numEntrada) == 1){
            printf("SIM\n");
        }else{
            printf("NAO\n");
        }
    }
}
