package org.socialsignin.spring.data.dynamodb.repository.query;

import java.io.Serializable;

import org.socialsignin.spring.data.dynamodb.query.Query;
import org.socialsignin.spring.data.dynamodb.query.QueryRequestMapper;
import org.socialsignin.spring.data.dynamodb.repository.support.DynamoDBEntityInformation;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.parser.PartTree;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDBCountQueryCreator<T,ID extends Serializable> extends AbstractDynamoDBQueryCreator<T, ID,Long> {

	private boolean pageQuery;
	
	public DynamoDBCountQueryCreator(PartTree tree,
			DynamoDBEntityInformation<T, ID> entityMetadata,
			DynamoDBMapper dynamoDBMapper, QueryRequestMapper queryRequestMapper,boolean pageQuery) {
		super(tree, entityMetadata, dynamoDBMapper, queryRequestMapper);
		this.pageQuery = pageQuery;
	}

	public DynamoDBCountQueryCreator(PartTree tree,
			ParameterAccessor parameterAccessor,
			DynamoDBEntityInformation<T, ID> entityMetadata,
			DynamoDBMapper dynamoDBMapper, QueryRequestMapper queryRequestMapper,boolean pageQuery) {
		super(tree, parameterAccessor, entityMetadata, dynamoDBMapper,
				queryRequestMapper);
		this.pageQuery = pageQuery;

	}
	
	@Override
	protected Query<Long> complete(DynamoDBQueryCriteria<T, ID> criteria, Sort sort) {
	
		return criteria.buildCountQuery(dynamoDBMapper, queryRequestMapper,pageQuery);

	}

}
