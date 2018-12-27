package com.ejemplo.usuariio.turismoabanquino;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import android.webkit.WebView;
import android.webkit.WebChromeClient;


class videoAdapte extends RecyclerView.Adapter<videoAdapte.VideoViewHolde> {

    List<YouTubeVideos>youtubeVideosList;

    public videoAdapte(){}


    public videoAdapte(List<YouTubeVideos>youtVideosList){
        this.youtubeVideosList=youtubeVideosList;
    }

    @NonNull
    @Override
    public VideoViewHolde onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_view,viewGroup,false);

        return new VideoViewHolde(view);

    }

    @Override
    public void onBindViewHolder(VideoViewHolde videoViewHolde, int i) {
        videoViewHolde.videoWeb.loadData(youtubeVideosList.get(i).getVideoUrl(),"text/html","utf-8");

    }

    @Override
    public int getItemCount() {
        return youtubeVideosList.size();
    }


    public class VideoViewHolde extends RecyclerView.ViewHolder {
        WebView videoWeb;
        public VideoViewHolde(@NonNull View itemView) {

            super(itemView);
            videoWeb = (WebView)itemView.findViewById(R.id.videoWebView);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient(){

            });
        }
    }
}
