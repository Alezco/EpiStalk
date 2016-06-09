package com.example.benja.epistalk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Home extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View self = inflater.inflate(R.layout.home, container, false);
        TextView cisco = (TextView) self.findViewById(R.id.textView2);
        TextView midlab = (TextView) self.findViewById(R.id.textView3);
        TextView sr = (TextView) self.findViewById(R.id.textView4);
        TextView sm14 = (TextView) self.findViewById(R.id.textView5);
        TextView total = (TextView) self.findViewById(R.id.textView7);
        TextView other = (TextView) self.findViewById(R.id.textView6);
        TextView othertotal = (TextView) self.findViewById(R.id.textView8);
        int ciscolen = RequestManager.getInstance().getCisco().size();
        int midlen = RequestManager.getInstance().getMidlab().size();
        int srlen = RequestManager.getInstance().getSr().size();
        int sm14len = RequestManager.getInstance().getSm14().size();
        int otherlen = RequestManager.getInstance().getOther().size();
        int totallen = ciscolen + midlen + srlen + sm14len;
        int othertotallen = totallen + otherlen;
        String ciscotext = "Cisco : " + ciscolen;
        String midtext = "Mid-lab : " + midlen;
        String srtext = "Lab-SR : " + srlen;
        String sm14text = "SM-14 : " + sm14len;
        String totaltext = "Total : " + totallen;
        String othertext = "Other : " + otherlen;
        String othertotaltext = "Other Total : " + othertotallen;
        cisco.setText(ciscotext);
        midlab.setText(midtext);
        sr.setText(srtext);
        sm14.setText(sm14text);
        total.setText(totaltext);
        other.setText(othertext);
        othertotal.setText(othertotaltext);
        return self;
    }
}
