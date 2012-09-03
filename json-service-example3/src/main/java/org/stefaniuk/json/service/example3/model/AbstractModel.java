package org.stefaniuk.json.service.example3.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * MVCS abstract model.
 * </p>
 * 
 * @author Daniel Stefaniuk
 * @version 1.1.0
 * @since 2012/09/02
 */
public abstract class AbstractModel {

    protected final Logger logger = LoggerFactory.getLogger(AbstractModel.class);

    /**
     * Returns identity key of the model.
     * 
     * @return Integer
     */
    public abstract Integer getId();

    public AbstractModel(Map<String, Object> row) {

        // TODO

    }

    /**
     * Returns database column name by given field name;
     * 
     * @param clazz
     * @param field
     * @return
     * @throws ModelException
     */
    public static String getColumnName(Class<? extends AbstractModel> clazz, String field) throws ModelException {

        String name = null;

        try {
            ModelColumn mc = clazz.getDeclaredField(field).getAnnotation(ModelColumn.class);

            // field must be marked as model column
            if(mc == null) {
                throw new ModelException("Cannot get column name of '" + field + "' filed");
            }

            name = mc.name();
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
        catch(NoSuchFieldException e) {
            e.printStackTrace();
        }

        return name;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("model: " + this.getClass()
            + ", hash: "
            + Integer.toHexString(this.hashCode())
            + ", fields: \n");

        Class<? extends AbstractModel> clazz = this.getClass();
        Field fields[] = clazz.getDeclaredFields();
        for(Field field: fields) {
            String name = field.getName();
            name = name.replace("_", "");
            String getterName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                Method method = clazz.getMethod(getterName);
                Object object = method.invoke(this);
                sb.append("   " + name + ": " + object + "\n");
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

}
