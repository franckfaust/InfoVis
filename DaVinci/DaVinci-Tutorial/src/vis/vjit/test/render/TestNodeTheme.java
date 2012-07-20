/*
 * 样式，定义线的粗细等
 */

package vis.vjit.test.render;

import davinci.rendering.ElemTheme;

public class TestNodeTheme extends ElemTheme {
	
}

/*
这里的p函数在父类中，其i也永远在父类中取
可以考虑在子类的构造器中为变量重新赋初值
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