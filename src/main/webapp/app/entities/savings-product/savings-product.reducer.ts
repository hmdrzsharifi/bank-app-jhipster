import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISavingsProduct, defaultValue } from 'app/shared/model/savings-product.model';

export const ACTION_TYPES = {
  FETCH_SAVINGSPRODUCT_LIST: 'savingsProduct/FETCH_SAVINGSPRODUCT_LIST',
  FETCH_SAVINGSPRODUCT: 'savingsProduct/FETCH_SAVINGSPRODUCT',
  CREATE_SAVINGSPRODUCT: 'savingsProduct/CREATE_SAVINGSPRODUCT',
  UPDATE_SAVINGSPRODUCT: 'savingsProduct/UPDATE_SAVINGSPRODUCT',
  DELETE_SAVINGSPRODUCT: 'savingsProduct/DELETE_SAVINGSPRODUCT',
  RESET: 'savingsProduct/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISavingsProduct>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type SavingsProductState = Readonly<typeof initialState>;

// Reducer

export default (state: SavingsProductState = initialState, action): SavingsProductState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SAVINGSPRODUCT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SAVINGSPRODUCT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SAVINGSPRODUCT):
    case REQUEST(ACTION_TYPES.UPDATE_SAVINGSPRODUCT):
    case REQUEST(ACTION_TYPES.DELETE_SAVINGSPRODUCT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SAVINGSPRODUCT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SAVINGSPRODUCT):
    case FAILURE(ACTION_TYPES.CREATE_SAVINGSPRODUCT):
    case FAILURE(ACTION_TYPES.UPDATE_SAVINGSPRODUCT):
    case FAILURE(ACTION_TYPES.DELETE_SAVINGSPRODUCT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SAVINGSPRODUCT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SAVINGSPRODUCT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SAVINGSPRODUCT):
    case SUCCESS(ACTION_TYPES.UPDATE_SAVINGSPRODUCT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SAVINGSPRODUCT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/savings-products';

// Actions

export const getEntities: ICrudGetAllAction<ISavingsProduct> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SAVINGSPRODUCT_LIST,
  payload: axios.get<ISavingsProduct>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ISavingsProduct> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SAVINGSPRODUCT,
    payload: axios.get<ISavingsProduct>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISavingsProduct> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SAVINGSPRODUCT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISavingsProduct> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SAVINGSPRODUCT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISavingsProduct> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SAVINGSPRODUCT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
