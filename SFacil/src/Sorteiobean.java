import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sun.org.apache.bcel.internal.generic.LLOAD;

import javafx.util.converter.LocalDateTimeStringConverter;

@ManagedBean
@SessionScoped
public class Sorteiobean {
	private String idSorteio = null;
	private String inicio = "0";
	private String fim = "0";
	private String desc;
	private List<String> lista = new ArrayList<>();
	private List<String> listaDesc = new ArrayList<>();
	String teste;
	SorteioDAO sfacil = new SorteioDAO();
	private int Nvezes = 0;
	int cont = 0;
	private String data = LocalDate.now().toString();
	
	public void limpar() {
		this.idSorteio = null;
		this.inicio = null;
		this.fim = null;
		this.desc = null;
		this.lista = new ArrayList<>();
		this.listaDesc = new ArrayList<>();
		this.teste = null;
		this.cont = 0;
	}

	public void setNvezes() {
		this.Nvezes = Integer.parseInt(getFim()) - Integer.parseInt(getInicio()) - listaDesc.size() + 1;
	}

	public String getDesc() {
		return this.desc;
	}
    public List<String> getListaDesc(){
    	return this.listaDesc;
    }
	public void pegalistadesc() {
		setNvezes();
		if (getDesc() != ""){
		String valores = getDesc();
		try {
			String[] arrayValores = valores.split("\\s");
			for (String s : arrayValores) {
				try {
					int zz = testeNumero(s);
					if ( zz > 0 && zz > (Integer.parseInt(getInicio())-1) && zz < (Integer.parseInt(getFim())+1)){
					  listaDesc.add(s);
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}

	public List<String> getLista() {
		return this.lista;
	}
    public Boolean getListav(){
    	return this.lista.isEmpty();
    }
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		String x = Integer.toString(testeNumero(fim));
		if (x.contains("-1")){
			setTeste("Número final inválido");
			this.fim = null;
			
		}else{
		    this.fim = x;
		}
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		String x = Integer.toString(testeNumero(inicio));
		if (x.contains("-1")){
			setTeste("Número inicial inválido");
			this.inicio = null;
		}else{
		    this.inicio = x;
		}
	}

	public int testeNumero(String numeroTeste) {
		try {
			int numero;
			numero = Integer.parseInt(numeroTeste);
			return numero;
		} catch (Exception e) {
			return -1;
		}
	}

	public void sortear() {
		
	  if ( getInicio() == null || getFim() == null){
		    setInicio("0"); 
		    setFim("0");
	  }else{	
		  this.teste = null;
		  setNvezes();
		  if ((Integer.parseInt(getInicio()) >= Integer.parseInt(getFim()))){
			  setTeste("Número inicial deve ser menor que número final");
		  } else {
			  if (cont >= Nvezes) {
				  setTeste("Todos os números possiveis foram sorteados");
			  }else{
			     aleatorio(testeNumero(inicio), testeNumero(fim));
			  }  
		  }
	   }
	}

	public int aleatorio(int ini, int fim) {
		Random random = new Random();
		int x = random.nextInt(fim - ini + 1);
		x = x + ini;
		if (listaDesc.isEmpty() == true) {
			pegalistadesc();
		}
		while (lista.contains(Integer.toString(x)) || listaDesc.contains(Integer.toString(x)) || x == 0) {
			x = random.nextInt((fim - ini + 1));
			x = x + ini;
		}
		cont++;
		if (x >= Integer.parseInt(getInicio()) & x <= Integer.parseInt(getFim())) {
			lista.add(Integer.toString(x));
			cadastrar();
		}
		return x;
	}

	public String getTeste() {
		return this.teste;
	}

	public void setTeste(String x) {
		this.teste = x;
	}
	public void cadastrar(){
		idSorteio= sfacil.SFacil( idSorteio, lista, inicio, fim, listaDesc, data);
		
	}
	public List<Sorteio> getListar(){
		return sfacil.listar();
	}
}
