package com.ubaid.Model.Templates;



import com.ubaid.controller.Controller;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;

public class InsertNewPicInTemplate
{
	public InsertNewPicInTemplate(Controller controller, String imgPath)
	{

		String img = "<img src=\"file:\\\\" + imgPath
				+ "\">";

		
        String jsCodeInsertHtml = "function insertHtmlAtCursor(html) {\n" +
                "    var range, node;\n" +
                "    if (window.getSelection && window.getSelection().getRangeAt) {\n" +
                "        range = window.getSelection().getRangeAt(0);\n" +
                "        node = range.createContextualFragment(html);\n" +
                "        range.insertNode(node);\n" +
                "    } else if (document.selection && document.selection.createRange) {\n" +
                "        document.selection.createRange().pasteHTML(html);\n" +
                "    }\n" +
                "}insertHtmlAtCursor('####html####')";

        Node webNode = controller.getHtmlEditorController().getHtmlEditor().lookup(".web-view");
        WebView webView = (WebView) webNode;
        WebEngine engine = webView.getEngine();
        
        
        try {
            engine.executeScript(jsCodeInsertHtml.
                    replace("####html####",
                            escapeJavaStyleString(img, true, true)));
        } catch (JSException e) {
            // A JavaScript Exception Occured
        }

				
	}
	
	
    private static String hex(int i) {
        return Integer.toHexString(i);
    }

	
	  private static String escapeJavaStyleString(String str,
	            boolean escapeSingleQuote, boolean escapeForwardSlash) {
	        StringBuilder out = new StringBuilder("");
	        if (str == null) {
	            return null;
	        }
	        int sz;
	        sz = str.length();
	        for (int i = 0; i < sz; i++) {
	            char ch = str.charAt(i);

	            // handle unicode
	            if (ch > 0xfff) {
	                out.append("\\u").append(hex(ch));
	            } else if (ch > 0xff) {
	                out.append("\\u0").append(hex(ch));
	            } else if (ch > 0x7f) {
	                out.append("\\u00").append(hex(ch));
	            } else if (ch < 32) {
	                switch (ch) {
	                    case '\b':
	                        out.append('\\');
	                        out.append('b');
	                        break;
	                    case '\n':
	                        out.append('\\');
	                        out.append('n');
	                        break;
	                    case '\t':
	                        out.append('\\');
	                        out.append('t');
	                        break;
	                    case '\f':
	                        out.append('\\');
	                        out.append('f');
	                        break;
	                    case '\r':
	                        out.append('\\');
	                        out.append('r');
	                        break;
	                    default:
	                        if (ch > 0xf) {
	                            out.append("\\u00").append(hex(ch));
	                        } else {
	                            out.append("\\u000").append(hex(ch));
	                        }
	                        break;
	                }
	            } else {
	                switch (ch) {
	                    case '\'':
	                        if (escapeSingleQuote) {
	                            out.append('\\');
	                        }
	                        out.append('\'');
	                        break;
	                    case '"':
	                        out.append('\\');
	                        out.append('"');
	                        break;
	                    case '\\':
	                        out.append('\\');
	                        out.append('\\');
	                        break;
	                    case '/':
	                        if (escapeForwardSlash) {
	                            out.append('\\');
	                        }
	                        out.append('/');
	                        break;
	                    default:
	                        out.append(ch);
	                        break;
	                }
	            }
	        }
	        return out.toString();
	    }

}
