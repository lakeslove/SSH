package com.lx.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.ui.Model;

public class StringEscapeUtils {
	
	public static Object escapeHtmlForBean(Object object) {
		if(object == null) {
			return null;
		}
		Class<?> srcClass = object.getClass();
		Object objNew = BeanUtils.instantiate(srcClass);
		BeanWrapper srcBeanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
		BeanWrapper dstBeanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(objNew);
		Field[] fields = srcClass.getDeclaredFields();
		for (Field field : fields) {
			Type fieldType = field.getGenericType();
			String fieldName = field.getName();
			if( srcBeanWrapper.isReadableProperty(fieldName) == false ||
				srcBeanWrapper.isWritableProperty(fieldName) == false ) {
				continue;
			}
			Object fieldValue = srcBeanWrapper.getPropertyValue(fieldName);
			if(fieldValue!=null) {
				if (fieldType.equals(String.class)) {
					fieldValue = escapeHtml((String)fieldValue);
				} else if(field.isAnnotationPresent((Class<? extends Annotation>) Model.class)) {
					fieldValue = escapeHtmlForBean(fieldValue);
				}
			}
			dstBeanWrapper.setPropertyValue(fieldName, fieldValue);
		}
		return objNew;
	}

	public static String escapeHtml(String string) {
		if (string != null) {
			string = string.replaceAll("&", "&amp;");
			string = string.replaceAll(" ", "&nbsp;");
			string = string.replaceAll("<", "&lt;");
			string = string.replaceAll(">", "&gt;");
			string = string.replaceAll("\"", "&quot;");
			string = string.replaceAll("\\\\", "&#92;");
			string = string.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		}
		return string;
	}
	
	public static String escapeHtmlNew(String str) {
		if ( StringUtils.isNotEmpty( str ) ) {

            StringBuilder builder = new StringBuilder();

            for ( char c : str.toCharArray() ) {

                switch ( c ) {

                    case '<':
                        builder.append( "&lt;" );
                        break;

                    case '>':
                        builder.append( "&gt;" );
                        break;

                    case '\'':
                        builder.append( "&#39;" );
                        break;

                    case '"':
                        builder.append( "&quot;" );
                        break;

                    case '&':
                        builder.append( "&amp;" );
                        break;

                    case '%':
                        builder.append( "&#37;" );
                        break;

                    case ';':
                        builder.append( "&#59;" );
                        break;

                    case '(':
                        builder.append( "&#40;" );
                        break;

                    case ')':
                        builder.append( "&#41;" );
                        break;

                    case '/':

                    	builder.append( "&#47;" );
                        break;

                    case ':':

                    	builder.append( "&#58;" );
                        break;

                    default:
                        builder.append( c );
                }
            }

            return builder.toString();
        }

        return str;
	}
	
	public static String composeHtml(String string) {
		if (string != null) {
			string = string.replaceAll("&amp;", "&");
			string = string.replaceAll("&nbsp;", " ");
			string = string.replaceAll("&lt;", "<");
			string = string.replaceAll("&gt;", ">");
			string = string.replaceAll("&quot;", "\"");
			string = string.replaceAll("&#92;", "\\\\");
			string = string.replaceAll("<br>", "\r\n");
		}
		return string;
	}
	
	public static Object composeHtmlForBean(Object object) {
		if(object == null) {
			return null;
		}
		Class<?> srcClass = object.getClass();
		Object objNew = BeanUtils.instantiate(srcClass);
		BeanWrapper srcBeanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
		BeanWrapper dstBeanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(objNew);
		Field[] fields = srcClass.getDeclaredFields();
		for (Field field : fields) {
			Type fieldType = field.getGenericType();
			String fieldName = field.getName();
			if( srcBeanWrapper.isReadableProperty(fieldName) == false ||
				srcBeanWrapper.isWritableProperty(fieldName) == false ) {
				continue;
			}
			Object fieldValue = srcBeanWrapper.getPropertyValue(fieldName);
			if(fieldValue!=null) {
				if (fieldType.equals(String.class)) {
					fieldValue = composeHtml((String)fieldValue);
				} else if(field.isAnnotationPresent((Class<? extends Annotation>) Model.class)) {
					fieldValue = composeHtmlForBean(fieldValue);
				}
			}
			dstBeanWrapper.setPropertyValue(fieldName, fieldValue);
		}
		return objNew;
	}
	
}
