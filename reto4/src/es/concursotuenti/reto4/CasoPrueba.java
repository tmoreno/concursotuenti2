package es.concursotuenti.reto4;

public class CasoPrueba implements Runnable {

	private int races;
	private int karts;
	private int nGrupos;
	private int [] grupos;
	private int litrosNecesarios;
	
	public CasoPrueba(int races, int karts, int nGrupos){
		this.races = races;
		this.karts = karts;
		this.nGrupos = nGrupos;
		this.grupos = new int [nGrupos];
		this.litrosNecesarios = 0;
	}

	public int getRaces() {
		return races;
	}

	public int getKarts() {
		return karts;
	}

	public int getnGrupos() {
		return nGrupos;
	}

	public int[] getGrupos() {
		return grupos;
	}
	
	public int getLitrosNecesarios() {
		return litrosNecesarios;
	}

	public void setGrupos(String[] aux) {
		for(int i = 0; i < grupos.length; i++){
			grupos [i] = Integer.parseInt(aux[i].trim());
		}
	}
	
	public void litrosNecesarios(){
		int kartsOcupados = 0;
		int participantesGrupo = 0;
		int grupoActual = 0; 
		int grupoSiguiente = 0;
		int primerGrupoCarreraActual = 0;
		
		for(int i = 0; i < races; i++){
			
			kartsOcupados = 0;
			primerGrupoCarreraActual = grupoActual;
			while(true){
				participantesGrupo = grupos[grupoActual];
				
				if(participantesGrupo + kartsOcupados <= karts){
					litrosNecesarios += participantesGrupo;
					kartsOcupados += participantesGrupo;
					grupoSiguiente = (grupoActual + 1) % nGrupos;
					
					if(grupoSiguiente != primerGrupoCarreraActual){
						grupoActual = grupoSiguiente;
					}
					else{
						break;
					}
				}
				else{
					break;
				}
			}
		}
	}

	@Override
	public void run() {
		litrosNecesarios();
	}
}
