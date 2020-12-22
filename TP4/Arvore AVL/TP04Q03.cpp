#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <err.h>
#include <math.h>
#include <time.h>

#define TAM_MAX_LINHA 250
#define NUMENTRADA 1000
#define TAMLINHA 1000

int comp = 0;


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
    printf(" %d ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
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

typedef struct No {
    Jogador elemento;
    struct No *esq, *dir;
    int nivel;

} No;

No* novoNo(Jogador *elemento) {
   No* novo = (No*) malloc(sizeof(No));
   novo->elemento = *elemento;
   novo->esq = NULL;
   novo->dir = NULL;
   novo->nivel = 1;
   return novo;
}

No* raiz;

int getMaior(int a, int b){
    if(a > b){
        return a;
    }else 
        return b;
}

int getNivel(No *no) {
    return (no == NULL) ? 0 : no->nivel;
}

void setNivel(No *no) {
    no->nivel = 1 + getMaior(getNivel(no->esq), getNivel(no->dir));
}

No* rotacionarDir(No *no) {
   // printf("Rotacionar DIR( %s) \n", no->elemento.nome);
    No* noEsq = no->esq;
    No* noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;

    setNivel(no);
    setNivel(noEsq);
    return noEsq;
}

No* rotacionarEsq(No *no) {
    //printf("Rotacionar ESQ(%s) \n", no->elemento.nome);
    No* noDir = no->dir;
    No* noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    setNivel(no);
    setNivel(noDir);
    return noDir;
}


No* balancear(No *no) {
        if(no != NULL){
         int fator = getNivel(no->dir) - getNivel(no->esq);

         //Se balanceada
         if (abs(fator) <= 1){
            setNivel(no);

         //Se desbalanceada para a direita
         }else if (fator == 2){

            int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);

            //Se o filho a direita tambem estiver desbalanceado
            if (fatorFilhoDir == -1) {
               no->dir = rotacionarDir(no->dir);
            }
            no = rotacionarEsq(no);

         //Se desbalanceada para a esquerda
         }else if (fator == -2){

            int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);

            //Se o filho a esquerda tambem estiver desbalanceado
            if (fatorFilhoEsq == 1) {
               no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);

            }else{
                printf("Erro fator de balanceamento (%d) invalido!", fator); 
            }
        }

      return no;
   }

void start(){
    raiz = NULL;
}

bool pesquisarRec(char x[], No* i) {
   bool resp;
    if (i == NULL) {
        resp = false;

    } else if (strcmp(x , i->elemento.nome) == 0) {
        comp++;
        resp = true;

    } else if (strcmp(x , i->elemento.nome) < 0 ) {
        comp++;
        printf(" esq");
        resp = pesquisarRec(x, i->esq);

    } else {
        printf(" dir");
        resp = pesquisarRec(x, i->dir);
    }
    return resp;
}

bool pesquisar(char x[]){
    printf("%s raiz", x);
    return pesquisarRec(x, raiz);
}


void mostrarCentralRec(No* i) {
   if (i != NULL) {
      mostrarCentralRec(i->esq);
      printf("%s \n", i->elemento.nome);
      mostrarCentralRec(i->dir);
   }
}

void mostrarCentral() {
   mostrarCentralRec(raiz);
}


void mostrarPreRec(No* i) {
   if (i != NULL) {
      printf("%s \n", i->elemento.nome);
      mostrarPreRec(i->esq);
      mostrarPreRec(i->dir);
   }
}

void mostrarPre() {
   mostrarPreRec(raiz);
}

void mostrarPosRec(No* i) {
   if (i != NULL) {
      mostrarPosRec(i->esq);
      mostrarPosRec(i->dir);
      printf("%s ", i->elemento.nome);
   }
}

void mostrarPos() {
   printf("[ ");
   mostrarPosRec(raiz);
   printf("]\n");
}


void inserirRec(Jogador *x, No** i) {
    if (*i == NULL) {
       *i = novoNo(x);

    } else if (strcmp(x->nome, (*i)->elemento.nome) < 0) {
        comp++;
      inserirRec(x, &((*i)->esq));

    } else if (strcmp(x->nome, (*i)->elemento.nome) > 0) {
        comp++;
      inserirRec(x, &((*i)->dir));

    } else {
      errx(1, "Erro ao inserir!");
    }
    
    *i = balancear(*i);
}

void inserir(Jogador *x) {
   inserirRec(x, &raiz);
}

void log(long cronometro){
    FILE *arq;
    arq = fopen("655938_avl.txt","w");
    fprintf(arq,"655938\t%ld\t%d",cronometro,comp);
    fclose(arq);
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
    long tempo = clock();                                   
    for(int i = 0; i < numEntrada; i++){
        ler(&jogador[i], referencia[atoi(entrada[i]) + 1]);
        inserir(&jogador[i]);
    }
    
    numEntrada = 0;
    bool existe;

    do{
        fgets(entrada[numEntrada], TAMLINHA, stdin);
    }while(isFim(entrada[numEntrada++]) == false);
    numEntrada--;

    for(int l = 0; l < numEntrada; l++){
        strtok(entrada[l], "\n");
        existe = pesquisar(entrada[l]);
        if(existe == false){
            printf(" NAO \n");
        }else{
            printf(" SIM \n");
        }
    }
    tempo = clock() - tempo;
    log(tempo);


    
}


