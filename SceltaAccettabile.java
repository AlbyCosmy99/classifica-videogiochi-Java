public class SceltaAccettabile{
    public static boolean sceltaAccettabile(int scelta){
        try{
            if(scelta == 1 || scelta == 2){
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }
}