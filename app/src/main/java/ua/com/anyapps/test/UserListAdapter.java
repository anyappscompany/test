package ua.com.anyapps.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ua.com.anyapps.test.Server.Users.Results;

public class UserListAdapter extends BaseAdapter {Context context;
    LayoutInflater lInflater;
    List<Results> objects;
    private static final String TAG = "debapp";

    UserListAdapter(Context context, List<Results> objects) {
        this.context = context;
        this.objects = objects;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = lInflater.inflate(R.layout.user_list_item, parent, false);
        }

        Results result = getProduct(position);
        ((TextView) v.findViewById(R.id.tvUserName)).setText(result.getName().getTitle() + " " + result.getName().getFirst() + " " + result.getName().getLast());
        Picasso.get().load(result.getPicture().getThumbnail()).placeholder( R.drawable.photo_load_progress).into((ImageView) v.findViewById(R.id.ivThumbnail));

        return v;
    }

    Results getProduct(int position) {
        return ((Results) getItem(position));
    }
}
