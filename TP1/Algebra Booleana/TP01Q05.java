class TP01Q05{
    public static boolean isZero(String s){
        return(s.length() >= 1 && s.charAt(0) == '0');
    }

    //public static boolean algebraBooleana(String s, oper, num1, num2){
        
    //}



    
    public static void main(String args[]){
        String[] entrada = new String[1000];
        String qtidadeOperacoes = "";
        String num1, num2, num3 = "";
        int numEntrada = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isZero(entrada[numEntrada++]) == false);
        numEntrada--;

        for(int i = 0; i < numEntrada; i++){
            qtidadeOperacoes = entrada[i].substring(0,1);
            if(qtidadeOperacoes == '2'){
                num1 = entrada[i].substring(2,3);
                num2 = entrada[i].substring(4,5);
            }else{
                num1 = entrada[i].substring(2,3);
                num2 = entrada[i].substring(4,5);
                num3 = entrada[i].substring(6,7);
            }
    
        }
    }
}
