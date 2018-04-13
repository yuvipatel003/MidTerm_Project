package yuvraj.midterm_project.ui.home.events;

/**
 * This is model  ( object which keep all the needed data) for a single page
 */

public class HomeAdapterPage {
    private final  int layoutId;
    private final String title;

    HomeAdapterPage(int layoutId, String title)
    {
        this.layoutId= layoutId;
        this.title= title;
    }

    public int getLayoutId()
    {
        return  layoutId;
    }

    public String getTitle() {
        return title;
    }
}

