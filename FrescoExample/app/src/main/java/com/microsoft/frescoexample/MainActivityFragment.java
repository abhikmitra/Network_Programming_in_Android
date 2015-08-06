package com.microsoft.frescoexample;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    String[] uriStr;
    int count = 0;
    public MainActivityFragment() {
        uriStr= new String[]{
                "https://scontent-sin1-1.xx.fbcdn.net/hphotos-xtf1/v/t1.0-9/1395342_739403912752920_85660189_n.jpg?oh=e07be8aa1f5efe514a7ec1fb0747de3c&oe=564CDE75",
                "https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png",
                "http://blueblots.com/wp-content/uploads/2011/10/1-DS-Fire-Texture-3.jpg",
                "https://scontent-sin1-1.xx.fbcdn.net/hphotos-xpa1/t31.0-8/736195_10151427736211584_640122226_o.jpg"
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final Uri uri = Uri.parse(uriStr[count]);
        final SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);

        ((Button)view.findViewById(R.id.imageChangeBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                final Uri u = Uri.parse(uriStr[count % 4]);
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(u)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(draweeView.getController())
                        .build();
                draweeView.setController(controller);
            }
        });
        return view;
    }

}
