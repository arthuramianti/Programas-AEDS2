#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

#define TAM_MAX_LINHA 250
#define NUMENTRADA 1000
#define TAMLINHA 1000

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

void swap(int a, int b, Jogador array[]){
    Jogador aux = array[a];
    array[a] = array[b];
    array[b] = aux;
}

void quicksortRec(Jogador array[], int esq, int dir) {
    int i = esq, j = dir;
    Jogador pivo = array[(dir+esq)/2];
    while (i <= j) {
        while (strcmp(array[i].estadoNascimento, pivo.estadoNascimento) < 0 || strcmp(array[i].estadoNascimento, pivo.estadoNascimento) == 0 && strcmp(array[i].nome, pivo.nome) < 0) i++;
        while (strcmp(array[j].estadoNascimento, pivo.estadoNascimento) > 0 || strcmp(array[j].estadoNascimento, pivo.estadoNascimento) == 0 && strcmp(array[j].nome, pivo.nome) > 0) j--;
        if (i <= j) {
            swap(i, j, array);
            i++;
            j--;
        }
    }
    if (esq < j)  quicksortRec(array, esq, j);
    if (i < dir)  quicksortRec(array, i, dir);
}

void quicksort(Jogador array[], int n) {
    quicksortRec(array, 0, n-1);
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
    int pos[numEntrada];

    for(int i = 0; i < numEntrada; i++){
        ler(&jogador[i], referencia[atoi(entrada[i]) + 1]);
    }

    quicksort(jogador, numEntrada);

    for(int i = 0; i < numEntrada; i++){
        imprimir(&jogador[i]);
    }
    

}


