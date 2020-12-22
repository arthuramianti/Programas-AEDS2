#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <err.h>

#define TAM_MAX_LINHA 250
#define NUMENTRADA 1000
#define TAMLINHA 1000
#define MAXTAM  6     // Tamanho da fila de jogadores


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
    printf(" ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
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

typedef struct Celula {
	Jogador elemento;        // Elemento inserido na celula.
	struct Celula* prox;     // Aponta a celula prox.
} Celula;

Celula* novaCelula(Jogador *elemento) {
   Celula *nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = *elemento;
   nova->prox = NULL;

   return nova;
}

Celula* primeiro;          //Fila 
Celula* ultimo;

void start () {
   Jogador jog;
   primeiro = novaCelula(&jog);
   ultimo = primeiro;
}

Jogador  remover() {
   if (primeiro == ultimo) {
      errx(1, "Erro ao remover!");
   }
   Celula* tmp = primeiro;
   primeiro = primeiro->prox;
   Jogador resp = primeiro->elemento;
   tmp->prox = NULL;
   free(tmp);
   tmp = NULL;

   return resp;
}

void inserir(Jogador *x) {
    int numElementos = 0;
    for(Celula* i = primeiro->prox; i != NULL; i = i->prox){
       numElementos++;
    }
    if(numElementos >= 5){
        remover();
    }

   ultimo->prox = novaCelula(x);
   ultimo = ultimo->prox;
}

void mostrar() {
    Celula* i;
    int indice = 0;
    for (i = primeiro->prox; i != NULL; i = i->prox) {
        printf("[%d]", indice++);
        imprimir(&i->elemento);
    }
}   

void media(){
    float somaAltura = 0;
    int aux = 0;
    float mediaAltura = 0;

    for(Celula* i = primeiro->prox; i != NULL; i = i->prox){
        somaAltura += i->elemento.altura;
        aux++;
    }

    mediaAltura += somaAltura / aux;
    if((mediaAltura - (int)mediaAltura) < 0.5){
        printf("%d\n", (int)mediaAltura);
    }else{
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
    int tam = 0;                                      
    for(int i = 0; i < numEntrada; i++){
        ler(&jogador[i], referencia[atoi(entrada[i]) + 1]);
        inserir(&jogador[i]);
        media();
    }

    char ref[2];
    fgets(ref, 3, stdin);
    int numOperacoes = atoi(ref);
    fgets(entrada[60], TAMLINHA, stdin);             //linha para pular espaço em branco "\n"

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

