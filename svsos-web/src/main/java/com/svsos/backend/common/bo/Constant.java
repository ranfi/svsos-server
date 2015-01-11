package com.svsos.backend.common.bo;

public class Constant {
	
	public enum MenuNodeStatus{
		ISLEAF_NODE(1, "子叶节点"), MENU_NODE(0, "目录节点");

		public final int value;
		public final String name;

		MenuNodeStatus(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}
	
	public enum MenuNodeType{
		TREE_START(0, "子树开始"), TYPE_CHILD_TREE(1, "树节点"), TYPE_CHILD_NODE(2, "子节点"),TREE_END(3, "子树结束");

		public final int value;
		public final String name;

		MenuNodeType(int value, String name) {
			this.value = value;
			this.name = name;
		}
	}
}
