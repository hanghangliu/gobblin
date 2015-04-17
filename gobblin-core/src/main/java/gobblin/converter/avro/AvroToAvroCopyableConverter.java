package gobblin.converter.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;

import gobblin.configuration.WorkUnitState;
import gobblin.converter.Converter;
import gobblin.converter.DataConversionException;
import gobblin.converter.SchemaConversionException;
import gobblin.converter.SingleRecordIterable;
import gobblin.fork.CopyableGenericRecord;
import gobblin.fork.CopyableSchema;


/**
 * Implementation of {@link Converter} that takes in an Avro {@link Schema} and {@link GenericRecord} and returns a
 * {@link gobblin.fork.CopyableSchema} and a {@link gobblin.fork.CopyableGenericRecord}.
 */
public class AvroToAvroCopyableConverter extends
    Converter<Schema, CopyableSchema, GenericRecord, CopyableGenericRecord> {

  /**
   * Returns a {@link gobblin.fork.CopyableSchema} wrapper around the given {@link Schema}.
   * {@inheritDoc}
   * @see gobblin.converter.Converter#convertSchema(java.lang.Object, gobblin.configuration.WorkUnitState)
   */
  @Override
  public CopyableSchema convertSchema(Schema inputSchema, WorkUnitState workUnit) throws SchemaConversionException {
    return new CopyableSchema(inputSchema);
  }

  /**
   * Returns a {@link gobblin.fork.CopyableGenericRecord} wrapper around the given {@link GenericRecord}.
   * {@inheritDoc}
   * @see gobblin.converter.Converter#convertRecord(java.lang.Object, java.lang.Object, gobblin.configuration.WorkUnitState)
   */
  @Override
  public Iterable<CopyableGenericRecord> convertRecord(CopyableSchema outputSchema, GenericRecord inputRecord,
      WorkUnitState workUnit) throws DataConversionException {
    return new SingleRecordIterable<CopyableGenericRecord>(new CopyableGenericRecord(inputRecord));
  }
}
