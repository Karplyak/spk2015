import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Scanner;


public class Traveler extends
	Applet
  implements Runnable {

  /**
   * ʳ������ ���.
   */
	protected int cityCount;

  /**
   * 
   * ʳ������ ��������.
   */
  protected int populationSize;

  /**
   * ʳ������ �������������� ��� �������.
   */
  protected double mutationPercent;

  /**
   * ������� ��������� ������ �� �����������.
   */
  protected int matingPopulationSize;

  /**
   * ��������� ��� �����������.
   */
  protected int favoredPopulationSize;

  /**
   * ������ ����� �������� ��� �����������.
   */
  protected int cutLength;

  /**
   * ������ ���������.
   */
  protected int generation;

  /**
   * ������ �������� �����.
   */
  protected Thread worker = null;

  /**
   * �������� �� ������� ������.
   */
  protected boolean started = false;

  /**
   * ������ ���.
   */
  protected City [] cities;

  /**
   * ������ ��������.
   */
  protected Chromosome [] chromosomes;

  /**
   * ������ ����.
   */
  private Button ctrlStart;

  /**
   * �������� ���� � ������� ���.
   */
  private TextField ctrlCities;

  /**
   * �������� ���� � ����������.
   */
  private TextField ctrlPopulationSize;

  /**
   * �������� ���� � �������� �������.
   */
  private TextField ctrlMutationPercent;

  /**
   * ����� ����� ��� ������ �� ��������� ����.
   */
  private Panel ctrlButtons;

  /**
   * ����.
   */
  private String status = "";
  private Scanner input;

  public void init()
  {
    setLayout(new BorderLayout());

    //
    ctrlButtons = new Panel();
    ctrlStart = new Button("����");
    ctrlButtons.add(ctrlStart);
    ctrlButtons.add(new Label("# ̳��:"));
    ctrlButtons.add(ctrlCities = new TextField(5));
    ctrlButtons.add(new Label("���������:"));
    ctrlButtons.add(ctrlPopulationSize = new TextField(5));
    ctrlButtons.add(new Label("% �������:"));
    ctrlButtons.add(ctrlMutationPercent = new TextField(5));
    this.add(ctrlButtons, BorderLayout.SOUTH);

    // 
    ctrlPopulationSize.setText("500");
    ctrlMutationPercent.setText("0.10");
    ctrlCities.setText("25");

    // 
    ctrlStart.addActionListener(new ActionListener()
    {

      public void actionPerformed(ActionEvent arg0)
      {
        startThread();
      }
    });

    started = false;
    update();
  }


  /**
   * ��������� ������.
   */
  public void startThread() {

	  try
	  {
		  cityCount = Integer.parseInt(ctrlCities.getText());
	  }
	  catch(NumberFormatException e)
	  {
		  cityCount = 25;
	  }

	  try
	  {
		  populationSize = Integer.parseInt(ctrlPopulationSize.getText());
	  }
	  catch(NumberFormatException e)
	  {
		  populationSize = 500;
	  }

	  try
	  {
		  mutationPercent = new Double(ctrlMutationPercent.getText()).doubleValue();
	  }
	  catch(NumberFormatException e)
	  {
		  mutationPercent = 0.10;
	  }

	  FontMetrics fm = getGraphics().getFontMetrics();
	  int bottom = ctrlButtons.getBounds().y - fm.getHeight()-2;

	  matingPopulationSize = populationSize/2;
	  favoredPopulationSize = matingPopulationSize/2;
	  cutLength = cityCount/5;

	  	//	 

	    cities = new City[cityCount];
	    for ( int i=0;i<cityCount;i++ ) {
	      cities[i] = new City(
	        (int)(Math.random()*(getBounds().width-10)),
	        (int)(Math.random()*(bottom-10)));
	    }
	    
	    /*try {
		input = new Scanner(new File("cities.txt"));
		String[] mass;
		int i=0;
		cityCount=0;
		cities = new City[cityCount];
		       while (input.hasNextLine()) {
	
	        	mass=input.nextLine().split(" ");
	        	System.out.println(Integer.parseInt(mass[0])+" "+ Integer.parseInt(mass[1]));
	           	cities[i] = new City(Integer.parseInt(mass[0]), Integer.parseInt(mass[1]));
	        	i++;cityCount++;

	                      }
	       
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	input.close();*/

	    // 

	    chromosomes = new Chromosome[populationSize];
	    for ( int i=0;i<populationSize;i++ ) {
	      chromosomes[i] = new Chromosome(cities);
	      chromosomes[i].setCut(cutLength);
	      chromosomes[i].setMutation(mutationPercent);
	    }
	    Chromosome.sortChromosomes(chromosomes,populationSize);

// 
    started = true;

    generation = 0;

    if ( worker != null )
        worker = null;
    worker = new Thread(this);
    worker.setPriority(Thread.MIN_PRIORITY);
    worker.start();
  }


  /**
   * �������� �����
   */
  public void update()
  {
    Image img = createImage(getBounds().width, getBounds().height);
    Graphics g = img.getGraphics();
    FontMetrics fm = g.getFontMetrics();

    int width = getBounds().width;
    int bottom = ctrlButtons.getBounds().y - fm.getHeight()-2;

    g.setColor(Color.black);//---------------------------------------------------------------------------------------
    g.fillRect(0, 0, width, bottom);

    if( started && (cities != null) )
    {
    	    g.setColor(Color.blue);//---------------------------------------------------------------------------------------
    	    for ( int i=0;i<cityCount;i++ ) {
    	      int xpos = cities[i].getx();
    	      int ypos = cities[i].gety();
    	      g.fillOval(xpos-5,ypos-5,10,10);
    	    }

    	    g.setColor(Color.yellow);//---------------------------------------------------------------------------------------
    	    for ( int i=0;i<cityCount;i++ ) {
    	      int icity = chromosomes[0].getCity(i);
    	      if ( i!=0 ) {
    	        int last = chromosomes[0].getCity(i-1);
    	        g.drawLine(
    	                  cities[icity].getx(),
    	                  cities[icity].gety(),
    	                  cities[last].getx(),
    	                  cities[last].gety());
    	      }
    	    }

    }


    g.drawString(status, 20, bottom-5);

    getGraphics().drawImage(img, 0, 0, this);

  }

  /**
   * �������� ����
   *
   * @param status ����.
   */
  public void setStatus(String status)
  {
    this.status = status;
  }

  /**
   * �������� ����.
   */
  public void run() {

    double thisCost = 500.0;
    double oldCost = 0.0;
    int countSame = 0;

    update();

    while(countSame<100) {

      generation++;

      int ioffset = matingPopulationSize;
      int mutated = 0;


      // ��������� �������� � ���������� ������ � ������ ���������� ������ �� �����������
      for ( int i=0;i<favoredPopulationSize;i++ ) {
        Chromosome cmother = chromosomes[i];
        // ���� ������� � ���������� ������ �� �����������
        int father = (int) ( 0.999999*Math.random()*(double)matingPopulationSize);
        Chromosome cfather = chromosomes[father];

        mutated += cmother.mate(cfather,chromosomes[ioffset],chromosomes[ioffset+1]);
        ioffset += 2;
      }

      // ����������� ������ �������� � ���� ��� ����������
      for ( int i=0;i<matingPopulationSize;i++ ) {
        chromosomes[i] = chromosomes[i+matingPopulationSize];
        chromosomes[i].calculateCost(cities);
      }


      // ����������
      Chromosome.sortChromosomes(chromosomes,matingPopulationSize);

      double cost = chromosomes[0].getCost();
      thisCost = cost;
      double mutationRate = 100.0 * (double) mutated / (double) matingPopulationSize;

      NumberFormat nf = NumberFormat.getInstance();
      nf.setMinimumFractionDigits(2);
      nf.setMinimumFractionDigits(2);

     
      setStatus("�������� "+generation+" ���� "+(int)thisCost+" �������� "+nf.format(mutationRate)+"%");


      if ( (int)thisCost == (int)oldCost ) {
        countSame++; 
      } else {
        countSame = 0;
        oldCost = thisCost;
      }
      update();


    }
    setStatus("����������� ���� ������� ���� "+generation+" �������."+" C������ ������� "+(int)thisCost);
    update();
  }

  public void paint(Graphics g)
  {
	  update();
  }
}
