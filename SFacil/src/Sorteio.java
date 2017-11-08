import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Sorteio {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int idSorteio;
  private String lista ;
  private String inicio;
  private String fim ;
  private String listaDesc ;
  private String data;
  
public String getLista() {
	return lista;
}
public String getIdSorteio(){
	return Integer.toString(idSorteio);
}
public void setIdSorteio( String id){
	if (id == null){
		this.idSorteio = -1;
	}else{
	this.idSorteio= Integer.parseInt(id);
	}
}
public void setLista(List<String> lista) {
	String a="";
	int x= lista.size();
	for (int i = 0; i < x ;i++){
		if (a == ""){
			a= lista.get(i); 	
			}else{
	        a = a + ", "+ lista.get(i);
	}}
	this.lista= a;
}
public String getInicio() {
	return inicio;
}
public void setInicio(String inicio) {
	this.inicio = inicio;
}
public String getFim() {
	return fim;
}
public void setFim(String fim) {
	this.fim = fim;
}
public String getListaDesc() {
	return listaDesc;
}
public void setListaDesc(List<String> listaDesc) {
	String a="";
	int x= listaDesc.size();
	for (int i = 0; i < x ;i++){
		if (a == ""){
			a= listaDesc.get(i); 	
			}else{
	        a = a + ", "+ listaDesc.get(i);
	}
	}
	this.listaDesc = a;
}
public String getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
}
