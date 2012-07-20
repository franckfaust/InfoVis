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
		// ��dispȡgraph
		Graph<IVisualNode> graph = (Graph<IVisualNode>) disp.getData("mygraph");
		if (null == graph) {
			return;
		}

		VisualNode node = null;
		int ncnt = graph.getNodeCount();
		// ��������nodes
		for (int i = 0; i < ncnt; ++i) {
			node = (VisualNode) graph.getNode(i);
			// ��λ�����õ���Ļ��һ���������������ƶ���ȥӦ�ú�animation�й�
			// Math.random()Ϊ[0.0, 1.0)
			node.setX(Math.random() * disp.getWidth());
			node.setY(Math.random() * disp.getHeight());
			// ���ÿ�β�ͬ����animationģ��ὥ��
			node.setWidth(Math.random() * 80);
			node.setHeight(Math.random() * 80);
		}
	}

	public void reset() {
	}

}
