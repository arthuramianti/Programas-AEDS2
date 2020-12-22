class q1{

    public void setFilhoFolha(char c){
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if(i.elemento == c){
                i.no.folha = true;
                i = ultimo;
            }
		}
    }

    public void inserir(String elemento) throws Exception {
        inserir(elemento, raiz, 0);
    }

    private void inserir(String elemento, No no, int i) throws Exception {
        No filho = no.pesquisar(elemento.charAt(i));

        if(filho == null){
            System.out.print("*****CRIANDO FILHO******");
            filho = no.inserir(elemento.charAt(i));

            if(i == elemento.length() - 1){
                //System.out.print("(folha)");
                no.setFilhoFolha(elemento.charAt(i));
            }else{
                inserir(elemento, filho, i + 1);
            }

        } else if (filho.folha == false && i < elemento.length() - 1){
            inserir(elemento, filho, i + 1);

        } else {
            throw new Exception("ERRO");
        } 
    }

}


QUESTAO 2


public int somaElementos(){
    int resultado = 0;

    for(int i = 0; i < T1.m1; i++){                //M1 sendo o tamanhao da tabela T1 sem a area de reserva
        if(T1[i] != null){
            resultado += T1[i];
        }
    }

    for(int i = 0; i < TAM; i++){                                 //TAM sendo o tamanhao das tabelas
        if(T2[i].dir != null && T2[i].esq != null){
            resultado +=  T2[i].somaArvore(T2[i].raiz, 0);
        }
    }

    for(int i = 0 ; i < TAM; i++){
        if(T3[i] != null){
            resultado += T3[i];
        }
    }

    for(int i = 0 ; i < TAM + TAMRESERVA; i++){
        if(T4[i] != null){
            resultado += T4[i];
        }
    }
}

public int somaArvore(No j, int soma){
    if (i != null) {
        mostrarCentral(i.esq); // Elementos da esquerda.
        soma += j.elemento; // Conteudo do no.
        mostrarCentral(i.dir); // Elementos da direita.
    }
    return soma;
}
