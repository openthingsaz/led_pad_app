package truesolution.ledpad.asign.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by TCH on 2020. 07. 01.
 *
 * @author think.code.help@gmail.com
 * @version 1.0
 * @since 2020. 07. 01.
 */
@Database(entities = {MD_Icon.class}, version = 1)
public abstract class MAppDatabase extends RoomDatabase {
	public abstract DAO_EmoticonList iconDAO();
}