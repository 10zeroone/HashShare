package kr.codedragon.android.hashshare;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FileTransfer extends Activity implements OnClickListener{

	Button btn_Email;
	TextView tvState;
	
	Uri URI = null;
	String path =null;
	String hashMD5 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ȭ�鿡 ������ ������ ���� �����ϴ� �Լ�
		setContentView(R.layout.activity_filetransfer);

		tvState = (TextView)findViewById(R.id.tvState);		

		//������ ��������
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();

		if(bundle== null){	//�����Ͱ� ������ �� �ִ� ���鰴ü�� null�̸� �����Ͱ� ������ �ǹ�		
			tvState.append("\n\n ���޹��� �����Ͱ� �����ϴ�!");
		}else{
			path = bundle.getString("msg");
			hashMD5 = bundle.getString("hashMD5");
			
			URI = Uri.parse("file://" + path);
			tvState.append("\n\n" + path);
		}
		
		btn_Email = (Button)findViewById(R.id.btn_Email);
		btn_Email.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if(v == btn_Email){
			Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, "");
            email.putExtra(Intent.EXTRA_SUBJECT, "���� ����");
            email.putExtra(Intent.EXTRA_TEXT, "���� ÷���մϴ�.\n MD5: " + hashMD5);
            if (URI != null) {
            	email.putExtra(Intent.EXTRA_STREAM, URI);
			}
            //email client ����
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "������ ���� ���α׷� ����:"));
		}
	}
}
