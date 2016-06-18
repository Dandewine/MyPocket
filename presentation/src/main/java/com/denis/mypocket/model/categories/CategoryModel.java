package com.denis.mypocket.model.categories;

import android.os.Parcelable;

/**
 * @author Denis_Zinkovskiy at 6/14/16.
 */

public interface CategoryModel extends Parcelable {
    String getName();
    String getId();
    int getPath();
}
