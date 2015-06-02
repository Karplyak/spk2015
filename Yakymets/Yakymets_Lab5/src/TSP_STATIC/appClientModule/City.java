
class City {

  /**
   * ���������� x ����.
   */
  int xpos;

  /**
   * ���������� y ����.
   */
  int ypos;

  /**
   * ����������� ����.
   * 
   * @param x ���������� x ����.
   * @param y ���������� y ����.
   */
  public City(int x, int y) {
    xpos = x;
    ypos = y;
  }

  /**
   * ������� ���������� x
   */
  public int getx() {
    return xpos;
  }

  /**
   *������� ���������� y .
   */
  public int gety() {
    return ypos;
  }

  /**
   * ������� ������� �� ������ ����.
   * 
   * @param cother ���� ����.
   * @return A �������.
   */
  public int proximity(City cother) {
    return proximity(cother.getx(),cother.gety());
  }

  /**
   * ������� ������� �� ��������� ����� �������������� ������� �������.
   * @param x ���������� x .
   * @param y ���������� y .
   * @return ³������.
   */
  public int proximity(int x, int y) {
    int xdiff = xpos - x;
    int ydiff = ypos - y;
    return(int)Math.sqrt( xdiff*xdiff + ydiff*ydiff );
  }
}
