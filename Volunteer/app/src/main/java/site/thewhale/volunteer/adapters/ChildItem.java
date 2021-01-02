package site.thewhale.volunteer.adapters;

public class ChildItem {

    private String ChildItemTitle;
    private int childItemPic;

    public ChildItem(String childItemTitle, int childItemPic)
    {
        this.ChildItemTitle = childItemTitle;
        this.childItemPic = childItemPic;
    }

    public String getChildItemTitle()
    {
        return ChildItemTitle;
    }

    public int getChildItemPic() {
        return childItemPic;
    }

    public void setChildItemPic(int childItemPic) {
        this.childItemPic = childItemPic;
    }

    public void setChildItemTitle(
            String childItemTitle)
    {
        ChildItemTitle = childItemTitle;
    }
}
