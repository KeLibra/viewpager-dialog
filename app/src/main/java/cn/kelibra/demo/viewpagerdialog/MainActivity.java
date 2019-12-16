package cn.kelibra.demo.viewpagerdialog;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import cn.kelibra.demo.viewpagerdialog.bean.DialogBean;
import cn.kelibra.demo.viewpagerdialog.dialog.ViewpagerDialog;

public class MainActivity extends AppCompatActivity {


    private List<DialogBean> dialogBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogBeans = initData();

        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewpagerDialog dialog = new ViewpagerDialog(MainActivity.this, dialogBeans);
                dialog.show();
            }
        });
    }

    /**
     * 测试数据
     */
    private List<DialogBean> initData() {
        List<DialogBean> dialogBeans = new ArrayList<>();
        dialogBeans.add(new DialogBean("http://chuantu.xyz/t6/706/1576477524x1031866013.jpg", "", "幼年格鲁特"));
        dialogBeans.add(new DialogBean("http://chuantu.xyz/t6/706/1576477028x2890211925.jpg", "", "成年格鲁特"));
        dialogBeans.add(new DialogBean("http://chuantu.xyz/t6/706/1576477544x2890211847.jpg", "", "暴走格鲁特"));
        return dialogBeans;
    }
}
