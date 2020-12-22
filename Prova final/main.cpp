#include <stdio.h>
#include <stdlib.h>
#define TAM 10000


typedef struct pilha {
    int topo;
    int entrada[TAM];
    int saida[TAM];
} Pilha;


Pilha* pilhaC();                               
int vazia(Pilha* pilha);
int cheia(Pilha* pilha, int vagas);
int inserir(Pilha* pilha, int c, int s, int vagas);
int pop(Pilha* pilha);

int main(){
    int k, n, ci, si;
    int i;

    while(1){
        scanf("%d %d", &n, &k);

        if(k == 0 && n == 0) break;

        Pilha* pilha = pilhaC();
        int possivel = 1;

        for(i = 0; i < n; i++){
            scanf("%d %d", &ci, &si);
            if(possivel){
                if(vazia(pilha)){
                    inserir(pilha, ci, si, k);
                    possivel = 1;
                } else {
                    while(ci >= pilha->saida[pilha->topo-1]){
                        pop(pilha);
                        if(vazia(pilha)) break;
                    }
                    if(cheia(pilha, k)){
                        possivel = 0;
                    } else {
                        inserir(pilha, ci, si, k);
                        if(pilha->topo > 1){
                            if(pilha->entrada[pilha->topo-1] > pilha->entrada[pilha->topo -2] && pilha->saida[pilha->topo -1] < pilha->saida[pilha->topo -2]){
                                possivel = 1;
                            }else{
                                possivel = 0;
                            }
                        } else {
                            possivel = 1;
                        }
                    }
                }
            }
        }
        if(possivel) printf("Sim\n");
        else printf("Nao\n");
    }
}

Pilha* pilhaC(){                                  
    Pilha* p = (Pilha*) malloc(sizeof(Pilha));
    p->topo = 0;
    return p;
}


int vazia(Pilha* pilha){
    if (pilha->topo == 0) return 1;
    else return 0;
}

int cheia(Pilha* pilha, int vagas){
    if (pilha->topo == vagas) return 1;
    else return 0;
}

int inserir(Pilha* pilha, int c, int s, int vagas){
    if (!cheia(pilha, vagas)){
        pilha->entrada[pilha->topo] = c;
        pilha->saida[pilha->topo] = s;
        pilha->topo++;
        return 1;
    } else {
        return 0;
    }
}

int pop(Pilha* pilha){
    if(!vazia(pilha)){
        pilha->topo--;
        return 1;
    } else {
        return 0;
    }
}
