package com.denis.domain.models.categories;

import android.os.Parcelable;

/**
 * @author Denis_Zinkovskiy at 6/14/16.
 */

public interface Category extends Parcelable {
    String getName();
    String getId();
    int getPath();

}
