#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <err.h>

#define NUMENTRADA 1000
#define TAMLINHA 1000
#define MAXTAM    1000
#define bool      short
#define true      1
#define false     0

struct Jogador{
    int id, altura, peso, anoNascimento;
    char nome[100], universidade[100], cidadeNascimento[100], estadoNascimento[100];
};

typedef struct CelulaDupla{
	struct Jogador elemento;        // Elemento inserido na celula.
	struct CelulaDupla* prox; // Aponta a celula prox.
    struct CelulaDupla* ant;  // Aponta a celula anterior.
    int index;
} CelulaDupla;

CelulaDupla* novaCelulaDupla(struct Jogador elemento) {
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = elemento;
   nova->ant = nova->prox = NULL;
   return nova;
}

CelulaDupla* primeiro;
CelulaDupla* ultimo;

void start(){
   struct Jogador a;
   primeiro = novaCelulaDupla(a);
   ultimo = primeiro;
}

int tamanho() {
   int tamanho = 0; 
   CelulaDupla* i;
   for(i = primeiro; i != ultimo; i = i->prox, tamanho++);
   return tamanho;
}

void inserirInicio(struct Jogador x, int pos) {
   CelulaDupla* tmp = novaCelulaDupla(x);
   tmp->index = pos;
   tmp->ant = primeiro;
   tmp->prox = primeiro->prox;
   primeiro->prox = tmp;
   if (primeiro == ultimo) {                    
      ultimo = tmp;
   } else {
      tmp->prox->ant = tmp;
   }
   tmp = NULL;
}




void inserirFim(struct Jogador x, int pos) {
   ultimo->prox = novaCelulaDupla(x);
   ultimo->prox->ant = ultimo;
   ultimo = ultimo->prox;
   ultimo->index = pos;
}

void inserir(struct Jogador x, int pos) {

   int tam = tamanho();

   if(pos < 0 || pos > tam){
      errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
   } else if (pos == 0){
      inserirInicio(x, pos);
   } else if (pos == tam){
      inserirFim(x, pos);
   } else {
      // Caminhar ate a posicao anterior a insercao
      CelulaDupla* i = primeiro;
      int j;
      for(j = 0; j < pos; j++, i = i->prox);

      CelulaDupla* tmp = novaCelulaDupla(x);
      tmp->index = pos;
      tmp->ant = i;
      tmp->prox = i->prox;
      tmp->ant->prox = tmp->prox->ant = tmp;
      tmp = i = NULL;
   }
}

Jogador removerInicio() {
    

    CelulaDupla *tmp = primeiro;
    primeiro = primeiro->prox;
    Jogador elemento = primeiro->elemento;
    primeiro->ant = NULL;
    tmp->prox = NULL;
    free(tmp);
    
    return elemento;

}

bool isFim(char s[]){
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

void ler(struct Jogador *j, char player[]){
    j->id = atoi(strsep(&player,","));

    strcpy(j->nome, strsep(&player , ","));    

    j->altura = atoi(strsep(&player,","));    

    j->peso = atoi(strsep(&player,","));    

    strcpy(j->universidade, strsep(&player , ","));
    if(strcmp(j->universidade, "")==0)
        strcpy(j->universidade, "nao informado");

    j->anoNascimento = atoi(strsep(&player,",")); 
    
    strcpy(j->cidadeNascimento, strsep(&player , ","));
    if(strcmp(j->cidadeNascimento, "")==0)
        strcpy(j->cidadeNascimento, "nao informado");
    
    for(int i=0; i<strlen(player); i++){
        if(player[i]=='\n'){
           player[i]='\0';
        }   
    }
    strcpy(j->estadoNascimento, player);
    if(strcmp(j->estadoNascimento, "")==0)
        strcpy(j->estadoNascimento, "nao informado");
    
}

void imprimir(struct Jogador *i){
    printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## %s]\n",i->id ,i->nome , i->altura, i->peso, i->anoNascimento, i->universidade, i->cidadeNascimento, i->estadoNascimento);
}

void imprimirLista(struct Jogador *i){
    printf(" ## %s ## %i ## %i ## %i ## %s ## %s ## %s ##\n",i->nome , i->altura, i->peso, i->anoNascimento, i->universidade, i->cidadeNascimento, i->estadoNascimento);
}

void mostrar(){
    CelulaDupla* i;
    for(i = primeiro->prox; i != NULL; i = i->prox){
        imprimir(&i->elemento);
    }
}


void swap(CelulaDupla* a, CelulaDupla* b){
    CelulaDupla* aux = novaCelulaDupla(a->elemento);
    a->elemento = b->elemento;
    b->elemento = aux->elemento;
    free(aux);
    aux = NULL;
}

 void quicksort(CelulaDupla* esq, CelulaDupla* dir){
    CelulaDupla* i = esq;
    CelulaDupla* j = dir;
    CelulaDupla* pivo = dir;

    while (i->index <= j->index) {
        while (strcmp(i->elemento.estadoNascimento, pivo->elemento.estadoNascimento) < 0 || strcmp(i->elemento.estadoNascimento, pivo->elemento.estadoNascimento) == 0 && strcmp(i->elemento.nome, pivo->elemento.nome) < 0){ i = i->prox; }
        while (strcmp(j->elemento.estadoNascimento, pivo->elemento.estadoNascimento) > 0 || strcmp(j->elemento.estadoNascimento, pivo->elemento.estadoNascimento) == 0 && strcmp(j->elemento.nome, pivo->elemento.nome) > 0){ j = j->ant; }
        if (i->index <= j->index){
            swap(i, j);
            i = i->prox;
            j = j->ant;
        }
    }

    if (esq->index < j->index)
        quicksort(esq, j);
    if (i->index < dir->index)  
        quicksort(i, dir);
    
}

int main(void){
    start();
    char entrada[NUMENTRADA][TAMLINHA];
    int numEntrada = 0;

    do{
        fgets(entrada[numEntrada],TAMLINHA, stdin);
    }while(isFim(entrada[numEntrada++]) == false);
    numEntrada--;
    
    char players[3923][200];
    
    int numLinha = 0;
    
    FILE * fPointer;
        fPointer = fopen("/tmp/players.csv", "r");   

        do{
            fgets(players[numLinha],200,fPointer);  
            numLinha++; 
        }while(numLinha<3923);

        fclose(fPointer);
    
    struct Jogador jogador[numEntrada];

    for(int i= 0; i < numEntrada; i++){
        ler(&jogador[i] , players[atoi(entrada[i])+1]);
    }

    for(int i = 0; i < numEntrada; i++){
            inserir(jogador[i], i);
    }
    
    char aux[100][100];


    quicksort(primeiro->prox,ultimo);
    mostrar();


}
