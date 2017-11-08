import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class SorteioDAO {
	private EntityManager em = Persistence.createEntityManagerFactory("SFacil").createEntityManager();

	public String SFacil(String idSorteio ,List<String> lista, String inicio, String fim,List<String> listaDesc, String data) {
		Sorteio u = new Sorteio();
		if (idSorteio != Integer.toString(-1)){
		    u.setIdSorteio(idSorteio);
		}
		u.setLista(lista);
		u.setInicio(inicio);
		u.setFim(fim);
		u.setListaDesc(listaDesc);
		u.setData(data);

		em.getTransaction().begin();
		if (u.getIdSorteio() == null || u.getIdSorteio().contentEquals("-1")){
		    em.persist(u);
		}else{
			u = em.merge(u);
		}
		em.getTransaction().commit();
		return u.getIdSorteio();
		
	}

	@SuppressWarnings("unchecked")
	public List<Sorteio> listar() {
		Query busca = em.createQuery("SELECT u FROM Sorteio u");
		return busca.getResultList();
	}
}
