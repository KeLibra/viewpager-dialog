package cn.kelibra.demo.viewpagerdialog.bean;

/**
 * @author: kezy
 * @create_time 2019/12/13
 * @description:
 */
public class DialogBean {


    /**
     * imageUrl : http://chuantu.xyz/t6/706/1576477028x2890211925.jpg ， http://chuantu.xyz/t6/706/1576477524x1031866013.jpg ， http://chuantu.xyz/t6/706/1576477544x2890211847.jpg
     * jumpUrl : https://mall.blshop1.com/product?productId=4
     * title: 标题
     */

    public String imageUrl;
    public String jumpUrl;
    public String title;

    public DialogBean(String adImage, String adUrl, String title) {
        this.imageUrl = adImage;
        this.jumpUrl = adUrl;
        this.title = title;
    }
}
