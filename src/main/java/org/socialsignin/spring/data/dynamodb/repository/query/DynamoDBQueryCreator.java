package org.socialsignin.spring.data.dynamodb.repository.query;

import java.io.Serializable;

import org.socialsignin.spring.data.dynamodb.query.Query;
import org.socialsignin.spring.data.dynamodb.query.QueryRequestMapper;
import org.socialsignin.spring.data.dynamodb.repository.support.DynamoDBEntityInformation;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.parser.PartTree;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDBQueryCreator<T,ID extends Serializable> extends AbstractDynamoDBQueryCreator<T, ID,T> {

	public DynamoDBQueryCreator(PartTree tree,
			DynamoDBEntityInformation<T, ID> entityMetadata,
			DynamoDBMapper dynamoDBMapper, QueryRequestMapper queryRequestMapper) {
		super(tree, entityMetadata, dynamoDBMapper, queryRequestMapper);
	}

	public DynamoDBQueryCreator(PartTree tree,
			ParameterAccessor parameterAccessor,
			DynamoDBEntityInformation<T, ID> entityMetadata,
			DynamoDBMapper dynamoDBMapper, QueryRequestMapper queryRequestMapper) {
		super(tree, parameterAccessor, entityMetadata, dynamoDBMapper,
				queryRequestMapper);
	}
	
	@Override
	protected Query<T> complete(DynamoDBQueryCriteria<T, ID> criteria, Sort sort) {
		if (sort != null) {
			criteria.withSort(sort);
		}

		return criteria.buildQuery(dynamoDBMapper, queryRequestMapper);

	}

}
