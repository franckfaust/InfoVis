/*
 * ��ʽ�������ߵĴ�ϸ��
 */

package vis.vjit.test.render;

import davinci.rendering.ElemTheme;

public class TestNodeTheme extends ElemTheme {
	
}

/*
�����p�����ڸ����У���iҲ��Զ�ڸ�����ȡ
���Կ���������Ĺ�������Ϊ�������¸���ֵ
class Base {
	protected int i = 0;

	public void p() {
		System.out.println(i);
	}
}

class Derived extends Base {
	protected int i = 2;
}

class Main {
	public static void main(String[] args) {
		Base b = new Base();
		Derived d = new Derived();

		b.p();
		d.p();
	}
}

output:
0
0
*/