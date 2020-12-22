class Q2{

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args){
        String[] entrada = new String[1000];
        String[] split = new String[30];
        String[] split2 = new String[30];
        int numEntrada = 0;
        int c = 0;
        int assasinato = 0;

        do{
            entrada[numEntrada] = MyIO.readLine();
        }while(isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        System.out.println("HALL OF MURDERES");
        
        for(int i = 0; i < numEntrada; i++){
            split = entrada[i].split(" ");
            String aux = split[0];
            String aux2 = split[1];

            c = i;

            /*for(int l = 0; l < numEntrada; l++){
                split2 = entrada[i].split(" ");
                if(!aux.equals(split2[1]) && l == numEntrada - 1){
                    MyIO.print(aux);
                    MyIO.println(" " + assasinato);
                }
                if(aux.equals(split2[0])){
                    assasinato++;
                }
            }*/

            while(isFim(entrada[c]) == false){
                split2 = entrada[c].split(" ");

                if(!aux.equals(split2[1]) && c == numEntrada -1){
                    System.out.print(aux);
                    MyIO.println(" " +  assasinato);
                }

                if(aux.equals(split2[0])){
                    assasinato++;
                }
               
                c++;
            }
            assasinato = 0;
            c = i;

            
        }

        


    }
}
