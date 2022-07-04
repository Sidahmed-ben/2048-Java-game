package code_source;

import java.util.Vector;

public class Grille_de_jeu {
    private int taille;
    private Cellule [][] tab_2d;
    private boolean gagne = false;
    private int score = 0;
    private int max = 0;
    
    public Grille_de_jeu(int taille, Cellule[][] tab_2d) {

        this.taille = taille;
        this.tab_2d = tab_2d;
    }
    
    public Grille_de_jeu(int taille) {

        this.taille = taille;
        this.tab_2d = new Cellule[taille][taille];
        for(int i = 0 ; i < taille ; ++i)
                for(int j = 0 ; j  < taille ; ++j)
                {
                    this.tab_2d[i][j] = new Cellule();
                    this.tab_2d[i][j].setContenu(0);
                    this.tab_2d[i][j].setPosition_vertical(taille - j - 1 );
                    this.tab_2d[i][j].setPosition_horisontal(i);
                    }
    }


    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Cellule[][] getTab_2d() {
        return tab_2d;
    }

    public void setTab_2d(Cellule[][] tab_2d) {
        this.tab_2d = tab_2d;
    }
    public boolean isGagne() {
        return gagne;
    }

    public void setGagne(boolean gagne) {
        this.gagne = gagne;
    }

    public void printGrille()
    {
        for(int i =0 ; i < this.taille;++i)
        {   
            System.out.println(" ");
            for(int j =0 ; j <  this.taille  ; ++j)
                System.out.print("| "+this.tab_2d[j][this.taille-i-1].getContenu()+" |");
        }
    }
    
    public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@SuppressWarnings("unused")
    private Vector<int[]>    celulles_Vides()
    {
        Vector<int[]> v = new Vector<int[]>();  
        
        for(int i =0 ; i < this.taille;++i)
    
            for(int j =0 ; j <  this.taille ; ++j)
            {
                if(this.tab_2d[i][j].getContenu() == 0)
                {
                    int [] var  = new int[2];
                    var[0] = this.tab_2d[i][j].getPosition_horisontal();
                    var[1] = this.tab_2d[i][j].getPosition_vertical();
                    
                    v.add(var);
                }
            }
        
        return v;
    }
    
    public void ajouter_Celulles()
    {
        Vector<int[]> v = this.celulles_Vides();
        int taille  = v.size();
        
        if(taille == 0)
            return ;
        
        int  index = (int) (Math.random() * taille);
        
        int proba  = (int) (Math.random() * 4);
        int contenu;
        
        if(proba ==  0)
        {
            contenu = 4;
        }
        else {
            contenu = 2;
        }
        
        
        this.tab_2d[v.get(index)[0]][this.taille-v.get(index)[1]-1].setContenu(contenu);
        
    }
    
    public boolean clic_droit()
    {
    	boolean retour;
        this.rotationDroite();
        this.rotationDroite();
        retour = this.clic_gauche();
        this.rotationGauche();
        this.rotationGauche();
        return retour;
    }
    public boolean clic_gauche()
    {
    	boolean retour = false;
        for(int j = 0 ; j < this.taille;++j)
        {   
            boolean modif = true;
            int debut = 1;
            while(modif)
            {   modif = false;
                
                
                for(int i = debut; i < taille ; ++i )
                {
                    int v1 =this.tab_2d[i][j].getContenu() ;
                    int v2 =this.tab_2d[i-1][j].getContenu();
                    if(v1 != 0)
                    {
                        if( v2 == 0 )
                        {	retour = true;
                            this.tab_2d[i-1][j].setContenu(v1);
                            this.tab_2d[i][j].setContenu(0);
                            modif = true;
                        }else {
                            if(v1== v2  )
                            {
                            	retour = true;
                            	this.score += v1 *2;
                                this.tab_2d[i-1][j].setContenu(v1*2);
                                this.tab_2d[i][j].setContenu(0);
                                modif = true;
                                debut = i + 2;
                                if(v1 *2 > this.max )
                                	this.max = v1*2;
                            }
                        }
                    }
                }
            }
        }
        
        return retour;
    }
    public boolean  clic_haut()
    {
    	boolean retour;
        this.rotationGauche();
        retour = this.clic_gauche();
        this.rotationDroite();
        
        return retour;
    }
    public boolean clic_bas()
    {
    	boolean retour;
        this.rotationDroite();
        retour = this.clic_gauche();
        this.rotationGauche();
        return retour;

    }
    
    Cellule tmp[][] ;
    public void rotationGauche()
    {
        tmp = new Cellule[this.taille][this.taille];
        for(int  i = 0;i < this.taille ; i++)
            for(int j = 0;j < this.taille ; ++j)
                tmp[i][j] = this.tab_2d[j][taille - i-1];
        
        this.tab_2d = tmp;
                
    }
    
    public void rotationDroite()
    {
        
        tmp = new Cellule[this.taille][this.taille];
        for(int  i = 0;i < this.taille ; i++)
            for(int j = 0;j < this.taille ; ++j)
                tmp[i][j] = this.tab_2d[this.taille-j -1][i];
        
        this.tab_2d = tmp;
                
    }
    
    public boolean gagnePremiereFois()
    {
        if(this.gagne)
        {
            return false;
        }
        
        for(int i  = 0 ; i < this.taille ;++i)
            for(int j = 0 ; j < this.taille; ++j)
                if(this.tab_2d[i][j].getContenu() == 2048)
                {
                    this.gagne = true;
                    return true;
                }
                    
        return false;
    }
    
    public boolean perdu()
    {
        
        Vector<int[]> v = this.celulles_Vides();
        
        if(v.size() != 0 )
            return false;
        for(int i = 0 ; i < this.taille   -1 ;++i )
            for(int j = 0 ; j < this.taille - 1 ;++j)
            {
                if(this.tab_2d[i][j].getContenu() == this.tab_2d[i+1][j].getContenu() || this.tab_2d[i][j].getContenu() == this.tab_2d[i][j+1].getContenu() )
                    return false;
            }
                
        for(int i = 0 ; i  < this.taille -1 ;++i)
            if(tab_2d[i][this.taille-1] == tab_2d[i+1][this.taille-1] || tab_2d[this.taille-1] [i]== tab_2d[this.taille-1][i+1] )
                return false;
        
        return true;
    }
    
    public int getcontenu(int i , int j )
    {
        return tab_2d[i][j].getContenu();
    }

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
    
    
    
    

}