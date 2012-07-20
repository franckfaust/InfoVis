/*
 * When the window is initialized or size is changed, 
 * layout() method will be automatically called
 */

package vis.vjit.test.layout;

import davinci.Display;
import davinci.ILayout;
import davinci.data.elem.IVisualNode;
import davinci.data.elem.VisualNode;
import davinci.data.graph.Graph;

public class TestLayout implements ILayout {

	public TestLayout() {
	}

	public String getName() {
		return "TestLayout";
	}

	public void layout(Display disp) {
		// 从disp取graph
		Graph<IVisualNode> graph = (Graph<IVisualNode>) disp.getData("mygraph");
		if (null == graph) {
			return;
		}

		VisualNode node = null;
		int ncnt = graph.getNodeCount();
		// 遍历所有nodes
		for (int i = 0; i < ncnt; ++i) {
			node = (VisualNode) graph.getNode(i);
			// 把位置设置到屏幕的一个比例，接下来移动过去应该和animation有关
			// Math.random()为[0.0, 1.0)
			node.setX(Math.random() * disp.getWidth());
			node.setY(Math.random() * disp.getHeight());
			// 如果每次不同，在animation模块会渐变
			node.setWidth(Math.random() * 80);
			node.setHeight(Math.random() * 80);
		}
	}

	public void reset() {
	}

}
