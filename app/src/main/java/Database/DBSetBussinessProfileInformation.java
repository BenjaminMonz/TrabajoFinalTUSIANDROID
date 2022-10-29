package Database;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mysql.jdbc.Blob;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Entidades.Comercio;
import Helpers.Helpers;

public class DBSetBussinessProfileInformation extends AsyncTask<Boolean, Void, Boolean> {

    private Context context;
    private Comercio comercio;
    private EditText txtNombre = null;
    private TextView textViewNombre = null;
    private TextView email = null;
    private EditText direccion = null;
    private ImageView imgLogo = null;
    private TextView cuil = null;
    private Bitmap bitmap;

    public DBSetBussinessProfileInformation() {
    }

    public EditText getTxtNombre() {
        return txtNombre;
    }

    public TextView getTextViewNombre() {
        return textViewNombre;
    }

    public void setTextViewNombre(TextView textViewNombre) {
        this.textViewNombre = textViewNombre;
    }

    public void setTxtNombre(EditText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public EditText getDireccion() {
        return direccion;
    }

    public void setDireccion(EditText direccion) {
        this.direccion = direccion;
    }

    public ImageView getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(ImageView imgLogo) {
        this.imgLogo = imgLogo;
    }

    public TextView getCuil() {
        return cuil;
    }

    public void setCuil(TextView cuil) {
        this.cuil = cuil;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private boolean getBussinesInformation() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from Comercios where id = " + comercio.getId());
            if (rs.next()) {
                bitmap = Helpers.getBitmapFromBytes((Blob) rs.getBlob("image"));
                comercio.setName(rs.getString("nombre"));
                comercio.setEmail(rs.getString("email"));
                comercio.setAddress(rs.getString("direccion"));
                comercio.setVatNumber(rs.getInt("cuil"));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected Boolean doInBackground(Boolean... Boolean) {
        return getBussinesInformation();
    }

    @Override
    protected void onPostExecute(Boolean response) {
        if (txtNombre != null) txtNombre.setText(comercio.getName());
        if (textViewNombre != null) textViewNombre.setText(comercio.getName());
        if (email != null) email.setText(comercio.getEmail());
        if (direccion != null) direccion.setText(comercio.getAddress());
        if (imgLogo != null) imgLogo.setImageBitmap(bitmap);
        if (cuil != null) cuil.setText(comercio.getVatNumber());
    }
}