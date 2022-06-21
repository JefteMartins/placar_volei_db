package util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseController {
    private SQLiteDatabase db;
    private CreateDatabase bank;
    public DatabaseController(Context context){
        bank = new CreateDatabase(context);
    }
                                //recebe os valores padronizado com o CreateBank que já estava pronto
    public String insertData(String nome_partida, String nome_time_A, String nome_time_B, int pontos_por_set, int num_sets, String vencedor){
        //string padrão para quando estiver vazio
        String returnMessage = "No Data Available";

        try{
            ContentValues values;
            long result;
            db = bank.getWritableDatabase();
            values = new ContentValues();
            //inserindo os valores nas colunas
            values.put(CreateDatabase.COLUNA_1, nome_partida);
            values.put(CreateDatabase.COLUNA_2, nome_time_A);
            values.put(CreateDatabase.COLUNA_3, nome_time_B);
            values.put(CreateDatabase.COLUNA_4, pontos_por_set);
            values.put(CreateDatabase.COLUNA_5, num_sets);
            values.put(CreateDatabase.COLUNA_6, vencedor);
            result = db.insert(CreateDatabase.TABELA, null, values);
            db.close();

            if (result ==-1) {
                returnMessage = "Error on adding the following data "+result;
            }else {
                returnMessage = "Sucess!";
            }

        } catch (Exception err){
            err.printStackTrace();
        }
        return returnMessage;
    }
}
